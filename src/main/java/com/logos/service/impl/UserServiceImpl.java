package com.logos.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.logos.dto.filter.SimpleFilter;
import com.logos.entity.User;
import com.logos.repository.UserRepository;
import com.logos.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllByFilter(SimpleFilter filter) {
		return userRepository.findAll(getSpecification(filter));
	}
	
	
	@Override
	public Page<User> findUsersByPage(Pageable pageable) {
		return userRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}
	
	@Override
	public Page<User> findUsersByPageFiltered(Pageable pageable, SimpleFilter filter) {
		return userRepository.findAll(getSpecification(filter), PageRequest.of(pageable.getPageNumber(), filter.getPageSize()));
	}
	
	private Specification<User> getSpecification(SimpleFilter filter) {
		return new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				final List<Predicate> predicates = new ArrayList<>();
			
				if (filter.getSearch().isEmpty() && filter.getMaxSalary().isEmpty() && filter.getMinSalary().isEmpty())
					return null;

				predicates.add(cb.like(root.get("firstName"), "%" + filter.getSearch() + "%"));
				predicates.add(cb.like(root.get("lastName"), "%" + filter.getSearch() + "%"));
				predicates.add(cb.like(root.get("email"), "%" + filter.getSearch() + "%"));
				predicates.add(cb.like(root.get("login"), "%" + filter.getSearch() + "%"));

				Predicate predicateSal;
				if(filter.getMinSalary().isEmpty() && filter.getMaxSalary().isEmpty())
					return cb.or(predicates.toArray(new Predicate[predicates.size()]));
				if(filter.getMaxSalary().isEmpty())
					predicateSal = cb.ge(root.get("salary"), Integer.parseInt(filter.getMinSalary()));
				else if(filter.getMinSalary().isEmpty())
					predicateSal = cb.le(root.get("salary"), Integer.parseInt(filter.getMaxSalary()));
				else 
					predicateSal = cb.between(root.get("salary"), Integer.parseInt(filter.getMinSalary()), Integer.parseInt(filter.getMaxSalary()));
			
				return cb.and(predicateSal, cb.or(predicates.toArray(new Predicate[predicates.size()])));
			}
		};
	}
}
