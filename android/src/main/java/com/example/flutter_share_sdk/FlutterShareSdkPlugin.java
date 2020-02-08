package com.example.flutter_share_sdk;

import android.app.Activity;
import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterShareSdkPlugin */
public class FlutterShareSdkPlugin implements MethodCallHandler {

  private Registrar registrar;
  private MethodChannel channel;
  FlutterShareSdkPlugin(Registrar registrar,MethodChannel channel){
    this.registrar = registrar;
    this.channel = channel;
  }

  private Activity getActivity(){
    return registrar.activity();
  }

  private Context getApplicationContext(){
    return registrar.activity().getApplicationContext();
  }


  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_share_sdk");
    channel.setMethodCallHandler(new FlutterShareSdkPlugin(registrar,channel));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if (call.method.equals("shartWeChat")){
      showShare(result);
    }else {
      result.notImplemented();
    }
  }

  private void showShare(Result result) {
    OnekeyShare oks = new OnekeyShare();
    // title标题，微信、QQ和QQ空间等平台使用
    oks.setTitle("");
    // titleUrl QQ和QQ空间跳转链接
    oks.setTitleUrl("http://sharesdk.cn");
    // text是分享文本，所有平台都需要这个字段
    oks.setText("我是分享文本");
    // imagePath是图片的本地路径，确保SDcard下面存在此张图片
    oks.setImagePath("/sdcard/test.jpg");
    // url在微信、Facebook等平台中使用
    oks.setUrl("http://sharesdk.cn");
    // 启动分享GUI
    oks.show(getApplicationContext());
    result.success("showShare ");
  }
}
