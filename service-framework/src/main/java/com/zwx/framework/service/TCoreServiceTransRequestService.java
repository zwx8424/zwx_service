package com.zwx.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwx.framework.domain.TCoreServiceTransRequest;
import com.zwx.framework.repository.TCoreServiceTransRequestRepository;
import com.zwx.framework.util.JsonUtils;

@Service
public class TCoreServiceTransRequestService {
	private static final Logger logger = LoggerFactory.getLogger(TCoreServiceTransRequestService.class);
	@Autowired
	TCoreServiceTransRequestRepository tCoreServiceTransRequestRepository;

	@Transactional
	public TCoreServiceTransRequest save(TCoreServiceTransRequest obj) {
		logger.info(JsonUtils.toJson(obj));
		return tCoreServiceTransRequestRepository.save(obj);
	}

	@Transactional
	public TCoreServiceTransRequest update(TCoreServiceTransRequest obj) {
		if (obj == null || obj.getTransRequestId() == null) {
			throw new IllegalArgumentException("when update ,object must be not null!");
		}
		logger.info(JsonUtils.toJson(obj));
		return tCoreServiceTransRequestRepository.save(obj);
	}
}
