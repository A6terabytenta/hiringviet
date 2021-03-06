package vn.com.hiringviet.testdao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import vn.com.hiringviet.dao.CompanyDAO;
import vn.com.hiringviet.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml" })
@Transactional
public class CompanyDaoTest {
	@Autowired
	private CompanyDAO companyDao;

	@Test
	public void should_return_hot_company() throws Exception {
		List<Company> companies = companyDao.getListCompanyHot(0, 10);
		assertTrue(companies.size() > 0);
	}
}
