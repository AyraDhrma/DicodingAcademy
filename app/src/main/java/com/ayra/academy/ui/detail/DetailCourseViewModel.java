package com.ayra.academy.ui.detail;

import androidx.lifecycle.ViewModel;

import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.data.ModuleEntity;
import com.ayra.academy.utils.DataDummy;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {
    private CourseEntity courseEntity;
    private String courseId;

    public CourseEntity getCourse() {
        for (int i = 0; i < DataDummy.generateDummyCourse().size(); i++) {
            CourseEntity entity = DataDummy.generateDummyCourse().get(i);
            if (entity.getCourseId().equals(courseId)) {
                courseEntity = entity;
            }
        }
        return courseEntity;
    }

    public List<ModuleEntity> getModules() {
        return DataDummy.generateDummyModule(getCourseId());
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
