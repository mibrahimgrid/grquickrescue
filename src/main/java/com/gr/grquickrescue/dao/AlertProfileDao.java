package com.gr.grquickrescue.dao;

import java.util.List;
import com.gr.grquickrescue.models.AlertProfile;

public interface AlertProfileDao extends GenericDao<AlertProfile, Integer> {

		public List<AlertProfile> findAlertProfilesByAccountId(Integer id);
}
