package com.example.mobilepaindiary.ui.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


    @Entity
    public class User {
        @PrimaryKey(autoGenerate = true)
        public int uid;
        @ColumnInfo(name = "steps")

        public String steps;

        @ColumnInfo(name = "temp")


        public float temp;
        @ColumnInfo(name = "hum")

        public float hum;

        @ColumnInfo(name = "pressure")
        public float pressure;
        @ColumnInfo(name = "email")
        public String email;
        @ColumnInfo(name = "position")
        public String position;
        @ColumnInfo(name = "mood")
        public String mood;
        @ColumnInfo(name = "level")
        public int level;
        @ColumnInfo(name = "date")
        public String date;


        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public User(String steps, float temp, float hum, float pressure, String email, String position, String mood, int level, String date) {
            this.steps = steps;
            this.temp = temp;
            this.hum = hum;
            this.pressure = pressure;
            this.email = email;
            this.position = position;
            this.mood = mood;
            this.level = level;
            this.date = date;
        }

        public String getSteps() {
            return steps;
        }

        public void setSteps(String steps) {
            this.steps = steps;
        }

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getHum() {
            return hum;
        }

        public void setHum(float hum) {
            this.hum = hum;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

