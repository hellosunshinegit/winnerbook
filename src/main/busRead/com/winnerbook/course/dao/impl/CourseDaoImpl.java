package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.dto.CourseRelease;
import com.winnerbook.course.dto.CourseTypeId;

@Repository("courseDao")
public class CourseDaoImpl  extends BaseDAO implements CourseDao{
	private static final String COURSE_MAPPER = "CourseMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Course> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER + "listByPage", parameter);
	}

	@Override
	public Course findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(COURSE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Course course) {
		return this.sqlSession.insert(COURSE_MAPPER+"insert",course);
	}

	@Override
	public int update(Course course) {
		return this.sqlSession.insert(COURSE_MAPPER+"update",course);
	}

	@Override
	public List<Map<String, Object>> getCourses(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourses",parameter);
	}

	@Override
	public List<Map<String, Object>> getMainGuests(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getMainGuests",parameter);
	}

	@Override
	public void insertCourseRelease(Map<String, Object> parameter) {
		this.sqlSession.update(COURSE_MAPPER+"insertCourseRelease", parameter);
	}

	@Override
	public void updateCourseRelease(Map<String, Object> parameter) {
		this.sqlSession.update(COURSE_MAPPER+"updateCourseRelease",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseRelease(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourseRelease",parameter);
	}

	@Override
	public List<Map<String, Object>> getVideos(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getVideos",parameter);
	}

	@Override
	public int getCoursesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getCoursesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int getMainGuestsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getMainGuestsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int getVideosCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getVideosCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getCourseDetail(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(COURSE_MAPPER+"getCourseDetail",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseFile(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourseFile",parameter);
	}
	
	@Override
	public int updateClickNum(Map<String, Object> parameter) {
		return this.sqlSession.update(COURSE_MAPPER+"updateClickNum",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseByBookListId(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourseByBookListId",parameter);
	}

	@Override
	public List<Map<String, Object>> getCourseAdminCreate(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourseAdminCreate",parameter);
	}

	@Override
	public List<Map<String, Object>> getAdminCourses(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getAdminCourses",parameter);
	}

	@Override
	public int getAdminCoursesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getAdminCoursesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int insertCourseTypeId(List<CourseTypeId> courseTypeIds) {
		return this.sqlSession.insert(COURSE_MAPPER+"insertCourseTypeId",courseTypeIds);
 	}

	@Override
	public int deleteCourseTypeId(Integer courseId) {
		return this.sqlSession.delete(COURSE_MAPPER+"deleteCourseTypeId",courseId);
	}

	@Override
	public List<Map<String, Object>> getFreeCourses(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getFreeCourses", parameter);
	}

	@Override
	public int getFreeCoursesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getFreeCoursesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Map<String, Object>> getMainGuestsByName(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getMainGuestsByName", parameter);
	}

	@Override
	public int getMainGuestsByNameCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(COURSE_MAPPER + "getMainGuestsByNameCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Map<String, Object>> getCourseReleases(Map<String, Object> parameter) {
		return this.sqlSession.selectList(COURSE_MAPPER+"getCourseReleases",parameter);
	}

	@Override
	public int deleteCourseReleases(Map<String, Object> parameter) {
		return this.sqlSession.delete(COURSE_MAPPER+"deleteCourseReleases",parameter);
	}

	@Override
	public int insertBathCourseRelease(List<CourseRelease> courseReleases) {
		return this.sqlSession.insert(COURSE_MAPPER+"insertBathCourseRelease",courseReleases);
	}
}
