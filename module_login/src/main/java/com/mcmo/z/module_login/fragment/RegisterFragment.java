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
import com.mcmo.z.commonlibrary.constants.RouteCons;
import com.mcmo.z.commonlibrary.net.ErrorMsg;
import com.mcmo.z.commonlibrary.net.RetrofitCallback;
import com.mcmo.z.commonlibrary.net.RetrofitManager;
import com.mcmo.z.commonlibrary.utils.LoadingDialog;
import com.mcmo.z.commonlibrary.utils.ToastUtil;
import com.mcmo.z.module_login.LoginActivity_MLogin;
import com.mcmo.z.module_login.R;
import com.mcmo.z.module_login.bean.LoginInfoBean;
import com.mcmo.z.module_login.net.ServiceApi_MLogin;

@Route(path = RouteCons.Login.REGISTER_FRAGMENT)
public class RegisterFragment extends Fragment {
    private EditText et_userName;
    private EditText et_password;
    private EditText et_repassword;
    private LoadingDialog loadingDialog = new LoadingDialog();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mlogin_fragment_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_password = view.findViewById(R.id.et_password_register);
        et_repassword = view.findViewById(R.id.et_repassword_register);
        et_userName = view.findViewById(R.id.et_userName_register);

        Button btn_register = view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_userName.getText().toString();
                String password = et_password.getText().toString().trim();
                String repassword = et_repassword.getText().toString().trim();
                if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
                    ToastUtil.showToastUtil(getContext(),"有信息未填写");
                    return;
                }
                if(!password.equals(repassword)){
                    ToastUtil.showToastUtil(getContext(),"两次输入密码不同");
                    return;
                }
                ServiceApi_MLogin api = RetrofitManager.getInstance().create(ServiceApi_MLogin.class);
                loadingDialog.show(getContext());
                api.register(userName,password,repassword).enqueue(new RetrofitCallback<LoginInfoBean>() {
                    @Override
                    public void onCallBack() {
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onSuccess(LoginInfoBean data) {
                        if(data == null){
                            // TODO: 2019/1/10 数据异常 
                            return;
                        }
                        BaseUserInfo baseUserInfo = new BaseUserInfo(data.id,data.username,data.icon,data.email,data.type);
                        UserInfoSaver.save(baseUserInfo);
                        Activity act = getActivity();
                        if(act != null && act instanceof LoginActivity_MLogin){
                            UserInfoSaver.save(baseUserInfo);
                            ((LoginActivity_MLogin) act).loginSuccessAndFinish();
                        }
                        
                    }

                    @Override
                    public void onFailed(ErrorMsg error) {
                        ToastUtil.showToastUtil(getContext(),error.getMsg());
                    }
                });
            }
        });
    }
}
