/*
 * Copyright (C) 2012 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kijlee.android.demo.ui.camera;

import android.net.Uri;
import com.google.zxing.Result;
import com.kijlee.android.demo.ui.camera.result.ResultHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 管理与响应从网页中的 HTTP 链接扫描的请求相关的功能。
 * See <a href="https://github.com/zxing/zxing/wiki/ScanningFromWebPages">ScanningFromWebPages</a>.
 *
 * @author Sean Owen
 */
final class ScanFromWebPageManager {

  private static final CharSequence CODE_PLACEHOLDER = "{CODE}";
  private static final CharSequence RAW_CODE_PLACEHOLDER = "{RAWCODE}";
  private static final CharSequence META_PLACEHOLDER = "{META}";
  private static final CharSequence FORMAT_PLACEHOLDER = "{FORMAT}";
  private static final CharSequence TYPE_PLACEHOLDER = "{TYPE}";

  private static final String RETURN_URL_PARAM = "ret";
  private static final String RAW_PARAM = "raw";

  private final String returnUrlTemplate;
  private final boolean returnRaw;

  ScanFromWebPageManager(Uri inputUri) {
    returnUrlTemplate = inputUri.getQueryParameter(RETURN_URL_PARAM);
    returnRaw = inputUri.getQueryParameter(RAW_PARAM) != null;
  }

  boolean isScanFromWebPage() {
    return returnUrlTemplate != null;
  }

  String buildReplyURL(Result rawResult, ResultHandler resultHandler) {
    String result = returnUrlTemplate;
    result = replace(CODE_PLACEHOLDER,
                     returnRaw ? rawResult.getText() : resultHandler.getDisplayContents(), result);
    result = replace(RAW_CODE_PLACEHOLDER, rawResult.getText(), result);
    result = replace(FORMAT_PLACEHOLDER, rawResult.getBarcodeFormat().toString(), result);
    result = replace(TYPE_PLACEHOLDER, resultHandler.getType().toString(), result);
    result = replace(META_PLACEHOLDER, String.valueOf(rawResult.getResultMetadata()), result);
    return result;
  }

  private static String replace(CharSequence placeholder, CharSequence with, String pattern) {
    CharSequence escapedWith = with == null ? "" : with;
    try {
      escapedWith = URLEncoder.encode(escapedWith.toString(), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // can't happen; UTF-8 is always supported. Continue, I guess, without encoding
    }
    return pattern.replace(placeholder, escapedWith);
  }

}
