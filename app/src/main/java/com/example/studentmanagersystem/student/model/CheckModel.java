package com.example.studentmanagersystem.student.model;

import com.example.studentmanagersystem.Utils.LogUtil;
import com.example.studentmanagersystem.callback.Callback;
import com.example.studentmanagersystem.entity.Check;
import com.example.studentmanagersystem.entity.SetCheck;

import java.util.Calendar;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class CheckModel {

    private static final String TAG = "CheckModel";

    public void checkRequest(final String checkKey, final String studentId, final CheckCallback callback) {
        BmobQuery<SetCheck> query = new BmobQuery<>();
        LogUtil.d(TAG, "checkRequest: " + checkKey);
        query.getObject(checkKey, new QueryListener<SetCheck>() {
            @Override
            public void done(SetCheck setCheck, BmobException e) {
                if (e == null) {
                    if (setCheck == null) {
                        callback.keyNotExist();
                    } else {
                        Long startTime = setCheck.getStartTime();
                        Long currentTime = Calendar.getInstance().getTimeInMillis();
                        if (((currentTime - startTime) / (60 * 1000)) > 5){
                            callback.checkTimeOut();
                        }else {
                            saveCheckInfo(studentId, checkKey, callback);
                        }
                    }
                }else {
                    callback.onError(e);
                }
            }
        });
    }

    private void saveCheckInfo(String studentId, String checkKey, final CheckCallback callback) {
        Check check = new Check();
        check.setStudentId(studentId);
        check.setCheckKey(checkKey);
        check.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    callback.onSuccess(null);
                }else{
                    callback.onError(e);
                }
            }
        });
    }

    public interface CheckCallback extends Callback {
        void checkTimeOut();

        void keyNotExist();
    }
}
