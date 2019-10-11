package com.ayra.academy.ui.bookmark;

import androidx.lifecycle.ViewModel;

import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.utils.DataDummy;

import java.util.List;

public class BookmarkViewModel extends ViewModel {

    public List<CourseEntity> getBookmarks() {
        return DataDummy.generateDummyCourse();
    }

}
