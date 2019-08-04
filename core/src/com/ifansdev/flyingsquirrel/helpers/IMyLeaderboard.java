package com.ifansdev.flyingsquirrel.helpers;

public interface IMyLeaderboard {

    void onShowLeaderboardsRequsted();  //показ таблицы лидеров

    void pushAccomplishments(int countScore);         //сохранение очков в таблицу лидеров
}
