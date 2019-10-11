package com.ayra.academy.ui.academy;

import androidx.lifecycle.ViewModel;

import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    public List<CourseEntity> getCourses() {
        return DataDummy.generateDummyCourse();
    }

}
