package org.openmrs.module.PSI.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.PSI.SHNDhisMultipleChoiceObsElement;
import org.openmrs.module.PSI.SHNDhisObsElement;
import org.openmrs.module.PSI.api.db.SHNDhisObsElementDAO;

class HibernateSHNDhisObsElementDAO implements SHNDhisObsElementDAO {

	protected final Log log = LogFactory.getLog(getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SHNDhisObsElement> getAllDhisElement() {
		// TODO Auto-generated method stub
		List<SHNDhisObsElement> lists = sessionFactory.getCurrentSession()
				.createQuery("from SHNDhisObsElement where voided = 0").list();
		if (lists.size() != 0) {
			return lists;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SHNDhisMultipleChoiceObsElement> getAllMultipleChoiceDhisElement() {
		// TODO Auto-generated method stub
		List<SHNDhisMultipleChoiceObsElement> lists = sessionFactory.getCurrentSession()
				.createQuery("from SHNDhisMultipleChoiceObsElement where voided = 0").list();
		if (lists.size() != 0) {
			return lists;
		} else {
			return null;
		}
	}
	
}
