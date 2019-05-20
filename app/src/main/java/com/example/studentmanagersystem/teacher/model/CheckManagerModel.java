package com.example.studentmanagersystem.teacher.model;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.entity.Check;
import com.example.studentmanagersystem.entity.SetCheck;
import com.example.studentmanagersystem.entity.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class CheckManagerModel {

    private static final String TAG = "CheckManagerModel";

    public void createCheck(String teacherId, final CheckManagerCallback callback) {
        final SetCheck setCheck = new SetCheck();
        setCheck.setTeacherId(teacherId);
        setCheck.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void getCheckKey(String teacherId, final CheckManagerCallback<SetCheck> callback) {
        BmobQuery<SetCheck> query = new BmobQuery<>();
        query.addWhereEqualTo("teacherId", teacherId);
        query.findObjects(new FindListener<SetCheck>() {
            @Override
            public void done(List<SetCheck> list, BmobException e) {
                if (e == null) {
                    callback.querySuccessCallback(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void startCheck(String checkKey, long startTime, final CheckManagerCallback callback) {
        SetCheck setCheck = new SetCheck();
        setCheck.setStartTime(startTime);
        setCheck.update(checkKey, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void loadCheckedInfo(String checkKey, final CheckManagerCallback<Check> callback) {
        BmobQuery<Check> query = new BmobQuery<>();
        query.include("User[userName|userId]");
        query.addWhereEqualTo("checkKey", checkKey);
        query.findObjects(new FindListener<Check>() {
            @Override
            public void done(List<Check> list, BmobException e) {
                if (e == null) {
                    LogUtil.d(TAG, "done: " + list.toString());
                    callback.querySuccessCallback(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public void loadUnCheckedInfo(List<Check> checkedList, final CheckManagerCallback<User> callback) {
        List<Integer> studentIdList = new ArrayList<>();
        for (Check checkedInfo : checkedList) {
            studentIdList.add(checkedInfo.getStudentId());
        }

        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("permission", 1);
        query.addWhereNotContainedIn("userId", studentIdList);

        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    LogUtil.d(TAG, "done: " + list.toString());
                    callback.querySuccessCallback(list);
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    public interface CheckManagerCallback<T> {
        void onSuccess();

        void onError(BmobException e);

        void querySuccessCallback(List<T> list);

    }
}
