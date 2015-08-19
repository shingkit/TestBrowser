package com.jwjx.browser.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jwjx.browser.MainActivity;
import com.jwjx.browser.R;
import com.jwjx.browser.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawerFragment extends Fragment {
    @Bind(R.id.tv_version)
    TextView tv_version;

    @Bind(R.id.ip)
    EditText et_ip;
    @Bind(R.id.path)
    EditText et_path;
    @Bind(R.id.page)
    EditText et_page;
    @Bind(R.id.btn_save)
    Button btn_save;

    private String ip;
    private String path;
    private String page;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context =getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_save)
    public void click(View view) {
        String ip = et_ip.getText().toString();
        String path = et_path.getText().toString();
        String page = et_page.getText().toString();

        if (TextUtils.isEmpty(ip)) return;
        if (TextUtils.isEmpty(path)) return;
        if (TextUtils.isEmpty(page)) return;

        SPUtils.getInstance().setIP(ip);
        SPUtils.getInstance().setPath(path);
        SPUtils.getInstance().setPage(page);
        String url = ip + path + page;

        ((MainActivity) getActivity()).loadUrl(url);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ip = SPUtils.getInstance().getIp();
        if (!TextUtils.isEmpty(ip))
            et_ip.setText(ip);
        path = SPUtils.getInstance().getPath();
        if (!TextUtils.isEmpty(path))
            et_path.setText(path);
        page = SPUtils.getInstance().getPage();
        if (!TextUtils.isEmpty(page))
            et_page.setText(page);

        try {
            String versionName=context.getPackageManager().getPackageInfo(getActivity().getPackageName(), PackageManager.GET_ACTIVITIES).versionName;
            tv_version.setText("版本："+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}
