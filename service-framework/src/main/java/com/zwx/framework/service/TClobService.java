package com.zwx.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwx.framework.domain.TCoreServiceTransClob;
import com.zwx.framework.repository.TClobRepository;

@Service
public class TClobService {
	@Autowired
	TClobRepository tClobRepository;

	public TCoreServiceTransClob save(TCoreServiceTransClob obj) {
		return tClobRepository.save(obj);
	}
}
