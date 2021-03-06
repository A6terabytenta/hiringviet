<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>

<title><spring:message code="label.home.title" /></title>
<!-- Local style -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/hiringviet/home/css/home.css'/>">
</head>
<body>
	<input type="hidden" id="get_job_hot" value="<c:url value='/job/hot' />" />
	<input type="hidden" id="first_item" value="${firstItem}" />
	<input type="hidden" id="max_item" value="${maxItem}" />
	<input type="hidden" id="current_page" value="${currentPage}" />

	<input type="hidden" id="text_company_follow" value="<spring:message code="label.home.button.follow_company" />" />
	<input type="hidden" id="text_title_salary" value="<spring:message code="label.home.title.salary" />" />
	<input type="hidden" id="text_title_post_date" value="<spring:message code="label.home.title.post_date"/>" />
	<input type="hidden" id="text_title_major" value="<spring:message code="label.home.title.major"/>" />
	<input type="hidden" id="text_total_employee" value="<spring:message code="label.home.title.total_employee"/>" />
	<input type="hidden" id="text_title_people" value="<spring:message code="label.home.title.people"/>" />
	<input type="hidden" id="none_value" value="<spring:message code='label.default.dropdown.none_value'></spring:message>">
	<div class="row">
		<div class="col m4">
			<div class="card-panel">
				<div class="panel-title">
					Điều kiện lọc
				</div>
				<div class="row">
					<div class="col m12">
						<ul class="collection fillter-wrapper">
							<li class="collection-item"><b>Career</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-category-list" id="filter-category-list">
									<li>
										<input type="checkbox" class="filled-in" id="category-all" /> 
										<label for="category-all">All</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Company</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-company-list" id="filter-company-list">
									<li>
										<input type="checkbox" class="filled-in" id="company-all" /> 
										<label for="company-all">All</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Date Posted</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-date-post-list" id="filter-date-post-list">
									<li>
										<input class="with-gap data-post-radio" name="datePosts" value="0" type="radio" id="date-post-All"/> 
										<label for="date-post-All">All</label>
									</li>
									<li>
										<input class="with-gap data-post-radio" name="datePosts" value="1" type="radio" id="date-post-1" /> 
										<label for="date-post-1">1 day ago</label>
									</li>
									<li>
										<input class="with-gap data-post-radio" name="datePosts" value="2" type="radio" id="date-post-3" /> 
										<label for="date-post-3">3 day ago</label>
									</li>
									<li>
										<input class="with-gap data-post-radio" name="datePosts" value="3" type="radio" id="date-post-5" /> 
										<label for="date-post-5">5 day ago</label>
									</li>
									<li>
										<input class="with-gap data-post-radio" name="datePosts" value="4" type="radio" id="date-post-7" /> 
										<label for="date-post-7">7 day ago</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Job Function</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-position-list" id="filter-position-list">
									<li>
										<input type="checkbox" class="filled-in" id="position-all"/> 
										<label for="position-all">All</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Skill</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-skill-list" id="filter-skill-list">
									<li>
										<input type="checkbox" class="filled-in" id="skill-all"/> 
										<label for="skill-all">All</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Salary</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-salary-list" id="filter-salary-list">
									<li>
										<input class="with-gap salary-radio" name="salary" value="0" type="radio" id="salary-all"  />
										<label for="salary-all">All</label>
									</li>
									<li>
										<input class="with-gap salary-radio" name="salary" value="1" type="radio" id="salary-500"  />
										<label for="salary-500">Dưới 500$</label>
									</li>
									<li>
										<input class="with-gap salary-radio" name="salary" value="2" type="radio" id="salary-1000"  />
										<label for="salary-1000">500$ - 1000$</label>
									</li>
									<li>
										<input class="with-gap salary-radio" name="salary" value="3" type="radio" id="salary-2000"  />
										<label for="salary-2000">1000$ - 2000$</label>
									</li>
									<li>
										<input class="with-gap salary-radio" name="salary" value="4" type="radio" id="salary-3000"  />
										<label for="salary-3000">2000$ - 3000$</label>
									</li>
									<li>
										<input class="with-gap salary-radio" name="salary" value="5" type="radio" id="salary-4000"  />
										<label for="salary-4000">Trên 3000$</label>
									</li>
								</ul>
							</li>
							<li class="collection-item"><b>Province</b><i class="material-icons right icon-arrow">keyboard_arrow_down</i>
								<ul class="margin-top-10 display-none filter-list filter-province-list" id="filter-province-list">
									<li>
										<input type="checkbox" class="filled-in" id="province-all"  />
										<label for="province-all">All</label>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="col m8 no-padding-on-med-and-down">
			<div class="card-panel">
				<div class="panel-title">
					<spring:message code="label.home.title.hot_career" />
				</div>
				<div id="job-list">
					<script type="text/javascript">
						var categoryList = new Array();
						var companyList = new Array();
						var positionList = new Array();
						var skillList = new Array();
						var provinceList = new Array();
						var jobList = new Array();
					</script>
					<c:forEach items="${jobList}" var="job">
						<input type="hidden" value="${job.jobCategory.categoryName}" class="category">
						<input type="hidden" value="${job.company.displayName}" class="company">
						<input type="hidden" value="${job.position.displayName}" class="position">
						<input type="hidden" value="${job.workAddress.district.province.provinceName}" class="address">
						<input type="hidden" value="${job.id}" class="jobId">

						<script type="text/javascript">
							var checkContain = $.inArray($('.category').val(), categoryList);
							if (checkContain < 0) {
								categoryList.push($('.category').val());
							}
							$('.category').remove();

							var checkContain = $.inArray($('.company').val(), companyList);
							if (checkContain < 0) {
								companyList.push($('.company').val());
							}
							$('.company').remove();

							var checkContain = $.inArray($('.position').val(), positionList);
							if (checkContain < 0) {
								positionList.push($('.position').val());
							}
							$('.position').remove();

							var checkContain = $.inArray($('.address').val(), provinceList);
							if (checkContain < 0) {
								provinceList.push($('.address').val());
							}
							$('.address').remove();

							var checkContain = $.inArray($('.jobId').val(), jobList);
							if (checkContain < 0) {
								jobList.push($('.jobId').val());
							}
							$('.jobId').remove();
						</script>
						<div class="job-item">
							<div class="job-box" id="${job.id}">
								<div class="location-sticky orange darken-1 province-${fn:replace(job.workAddress.district.province.provinceName, ' ','')}">${job.workAddress.district.province.provinceName}</div>
								<div class="row none-margin-bottom">
									<div class="col m3 center hide-on-med-and-down">
										<a href="/company?companyId=${job.company.id}">
											<img src="${job.company.avatar}"
											class="responsive-img company-logo"></a> <a href="#"
											class="btn margin-top-10 orange darken-1 waves-effect waves-light">
											<spring:message code="label.home.button.follow_company" />
										</a>
									</div>
									<div class="col m9">
										<div class="col m12 p-0">
											<h1 class="col m9 p-0 title block-with-text">
												<c:choose>
													<c:when test="${job.company.isVip == 1}">
														<a class="hot"
															href="<c:url value='/company/careers/${job.id}' />">${job.title}</a>
													</c:when>
													<c:otherwise>
														<a class="not-hot"
															href="<c:url value='/company/careers/${job.id}' />">${job.title}</a>
													</c:otherwise>
												</c:choose>
											</h1>
										</div>
										<a href="#" class="company-name company-${fn:replace(job.company.displayName, ' ','')}">${job.company.displayName}</a>
										<p class="work-location">
											<a href="#">${job.workAddress.district.province.provinceName}</a>
										</p>

										<div class="job-info">
											<div class="row">
												<div class="col m6 none-padding-left">
													<p>
														<i class="material-icons prefix-icon">attach_money</i>
														<spring:message code="label.home.title.salary" />
														: <span class="info"><span class="minSalary">${job.minSalary}</span> -
															<span class="maxSalary">${job.maxSalary}</span></span>
													</p>
												</div>
												<div class="col m6 none-padding-left">
													<p class="right">
														<i class="material-icons prefix-icon">date_range</i>
														<spring:message code="label.home.title.post_date" />
														: <span class="info datePost">${job.postDate}</span>
													</p>
												</div>
												<div class="col m6 none-padding-left">
													<p>
														<i class="material-icons prefix-icon">loyalty</i>
														<spring:message code="label.home.title.major" />
														: <span class="info position-${fn:replace(job.position.displayName, ' ','')}">${job.position.displayName}</span>
													</p>
												</div>
												<div class="col m6 none-padding-left">
													<p class="right">
														<i class="material-icons prefix-icon">people</i>
														<spring:message code="label.home.title.total_employee" />
														: <span class="info">${job.size} <spring:message
																code="label.home.title.people" /></span>
													</p>
												</div>
											</div>
											<div class="row">
												<p
													class="col m12 none-padding-left text-justify block-with-text">
													${job.description}</p>
												<div class="col m12 none-padding-left margin-top-5">
													<c:forEach items="${job.skillSet}" var="skill">
														<input hidden="" value="${skill.displayName}" class="skill">
														<script type="text/javascript">
															var checkContain = $.inArray($('.skill').val(), skillList);
															if (checkContain < 0) {
																skillList.push($('.skill').val());
															}
															$('.skill').remove();
														</script>
														<a class="chip skill-${fn:replace(skill.displayName, ' ','')}">${skill.displayName}</a>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="text-align-center margin-top-10">
					<c:choose>
						<c:when test="${isDisabledLoadJob == true}">
							<a id="btn-load-more"
								class="btn-floating btn-large waves-effect waves-light red disabled"
								disabled="disabled"> <i class="material-icons">add</i>
							</a>
						</c:when>
						<c:otherwise>
							<a id="btn-load-more"
								class="btn-floating btn-large waves-effect waves-light red">
								<i class="material-icons">add</i>
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<!-- Local js -->
	<script type="text/javascript" src="<c:url value='/resources/hiringviet/home/js/home.js'/>"></script>
</body>
</html>