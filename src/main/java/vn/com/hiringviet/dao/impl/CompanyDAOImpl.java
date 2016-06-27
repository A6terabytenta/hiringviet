package vn.com.hiringviet.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.hiringviet.common.PublishResponseEnum;
import vn.com.hiringviet.common.StatusRecordEnum;
import vn.com.hiringviet.dao.CompanyDAO;
import vn.com.hiringviet.model.Account;
import vn.com.hiringviet.model.Company;
import vn.com.hiringviet.model.Job;
import vn.com.hiringviet.model.Post;

@Repository
@Transactional
public class CompanyDAOImpl extends CommonDAOImpl<Company> implements
		CompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getListCompanyHot(Integer first, Integer max) {

		StringBuilder hql = new StringBuilder();
		hql.append("FROM Company as c ");
		hql.append("WHERE c.changeLog.status = :status ");
		hql.append("ORDER BY c.changeLog.createdDate");
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql.toString());
		query.setFirstResult(first);
		query.setMaxResults(max);

		query.setParameter("status", StatusRecordEnum.ACTIVE.getValue());

		List<Company> companyList = query.list();
		return companyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getListCompanySuggest(Integer first, Integer max) {

		StringBuilder hql = new StringBuilder();
		hql.append("FROM Company as c ");
		hql.append("WHERE c.changeLog.status = :status ");
		hql.append("ORDER BY c.changeLog.createdDate");
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql.toString());
		query.setFirstResult(first);
		query.setMaxResults(max);

		query.setParameter("status", StatusRecordEnum.ACTIVE.getValue());

		List<Company> companyList = query.list();
		return companyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getListPosts(Integer first, Integer max, Integer companyId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Post.class, "post");
		criteria.createAlias("post.company", "company");
		criteria.createAlias("post.changeLog", "changeLog");
		criteria.add(Restrictions.eq("company.id", companyId));
		criteria.add(Restrictions.eq("changeLog.status",
				StatusRecordEnum.ACTIVE.getValue()));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		criteria.addOrder(Order.desc("changeLog.updatedDate"));

		List<Post> result = (List<Post>) criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getListJob(Integer first, Integer max, Integer companyId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Job.class, "job");
		criteria.createAlias("job.company", "company");
		criteria.createAlias("job.changeLog", "changeLog");
		criteria.add(Restrictions.eq("company.id", companyId));
		criteria.add(Restrictions.eq("changeLog.status",
				StatusRecordEnum.ACTIVE.getValue()));
		criteria.add(Restrictions.eq("job.isPublish",
				PublishResponseEnum.PUBLISH.getValue()));
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		criteria.addOrder(Order.desc("changeLog.updatedDate"));

		List<Job> result = (List<Job>) criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company getCompanyByAccount(Account account) {
		String hql = "FROM Company WHERE account=:account";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("account", account);
		List<Company> list = query.list();
		return list.isEmpty() ? null : list.get(0);
	}
}
