package edu.huflit.truyentranh.adapter;

import edu.huflit.truyentranh.model.Chapter;

public interface IActionRecycler {
    void sendActivity(String url);

    void deleteChapter(int id);

    void editChapter(Chapter chapter);
}
