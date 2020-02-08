import 'dart:async';

import 'package:flutter/services.dart';

class FlutterShareSdk {
  static const MethodChannel _channel =
      const MethodChannel('flutter_share_sdk');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get shartWeChat async {
    final String version = await _channel.invokeMethod('shartWeChat');
    print(version);
    return version;
  }
}
