package com.mcmo.z.module_login.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mcmo.z.commonlibrary.busniess.BaseUserInfo;
import com.mcmo.z.commonlibrary.busniess.UserInfoSaver;
import com.mcmo.z.module.baselibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.module.baselibrary.net.RetrofitManager;
import com.mcmo.z.commonlibrary.utils.ToastUtil;
import com.mcmo.z.module_login.LoginActivity;
import com.mcmo.z.module_login.R;
import com.mcmo.z.module_login.bean.LoginInfoBean;
import com.mcmo.z.module_login.net.ServiceApi_MLogin;

@Route(path = RouteCons.Login.LOGIN_FRAGMENT)
public class LoginFragment extends Fragment {
    private EditText et_userName;
    private EditText et_password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mlogin_fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_password = view.findViewById(R.id.et_password);
        et_userName = view.findViewById(R.id.et_userName);
        Button btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_userName.getText().toString();
                String password = et_password.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    ToastUtil.showToastUtil(getContext(),"填写用户名");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ToastUtil.showToastUtil(getContext(),"填写密码");
                    return;
                }
                ServiceApi_MLogin api = RetrofitManager.getInstance().create(ServiceApi_MLogin.class);
                api.login(userName,password).enqueue(new RetrofitCallback<LoginInfoBean>() {
                    @Override
                    public void onSuccess(LoginInfoBean data) {
                        if(data==null){
                            // TODO: 2019/1/10 返回数据异常
                            return;
                        }
                        ToastUtil.showToastUtil(getContext(),"登陆成功");
                        Activity act = getActivity();
                        if(act != null && act instanceof LoginActivity){
                            BaseUserInfo baseUserInfo = new BaseUserInfo(data.id,data.username,data.icon,data.email,data.type);
                            UserInfoSaver.save(baseUserInfo);
                            ((LoginActivity) act).loginSuccessAndFinish();
                        }
                    }

                    @Override
                    public void onFailed(ErrorMsg error) {
                        ToastUtil.showToastUtil(getContext(),"登陆失败");
                    }
                });
            }
        });
        Button btn_register = view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity act = getActivity();
                if(act instanceof LoginActivity){
                    ((LoginActivity) act).changeFragmentToRegister();
                }
            }
        });

    }
}
