package vn.com.hiringviet.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.hiringviet.common.StatusRecordEnum;
import vn.com.hiringviet.dao.JobDAO;
import vn.com.hiringviet.model.Job;
import vn.com.hiringviet.util.DateUtil;
import vn.com.hiringviet.util.Utils;

@Repository
@Transactional
public class JobDAOImpl extends CommonDAOImpl<Job> implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Job getJobByID(Integer jobId) {

		Session session = sessionFactory.getCurrentSession();

		Job job = (Job) session.get(Job.class, jobId);

		Hibernate.initialize(job.getSkillList());

		return job;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getListJobHot(Integer first, Integer max, List<Integer> skills) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Job.class, "job");
		criteria.createAlias("job.changeLog", "changeLog");
		criteria.createAlias("job.company", "company");

		if (!Utils.isEmptyList(skills)) {
			criteria.createAlias("job.skillList", "skillList");
		}

		criteria.add(Restrictions.eq("changeLog.status", StatusRecordEnum.ACTIVE.getValue()));
		criteria.add(Restrictions.gt("job.expiredDate", DateUtil.now()));

		if (!Utils.isEmptyList(skills)) {
			criteria.add(Restrictions.in("skillList.id", skills));
		}

		criteria.addOrder(Order.desc("company.isVip"));
		criteria.addOrder(Order.desc("changeLog.createdDate"));
		criteria.addOrder(Order.desc("job.minSalary"));
		criteria.addOrder(Order.desc("job.maxSalary"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);

		List<Job> jobList = criteria.list();
		return jobList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getListJobSuggest(Integer first, Integer max, List<Integer> skills) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Job.class, "job");
		criteria.createAlias("job.changeLog", "changeLog");
		criteria.createAlias("job.company", "company");

		if (!Utils.isEmptyList(skills)) {
			criteria.createAlias("job.skillList", "skillList");
		}

		criteria.add(Restrictions.eq("changeLog.status", StatusRecordEnum.ACTIVE.getValue()));
		criteria.add(Restrictions.gt("job.expiredDate", DateUtil.now()));

		if (!Utils.isEmptyList(skills)) {
			criteria.add(Restrictions.in("skillList.id", skills));
		}

		criteria.addOrder(Order.desc("company.isVip"));
		criteria.addOrder(Order.desc("changeLog.createdDate"));
		criteria.addOrder(Order.desc("job.minSalary"));
		criteria.addOrder(Order.desc("job.maxSalary"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);

		List<Job> jobList = criteria.list();
		return jobList;
	}
}
