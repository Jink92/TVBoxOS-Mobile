# TvBox (TVBoxMobile)

基于 [TVBoxOS](https://github.com/q215613905/TVBoxOS) 二次开发的安卓电视/盒子影视应用，支持视频点播、电视直播、DLNA 投屏、本地播放等功能。

- 包名：`com.github.tvbox.osc`
- 版本：1.1.1（`MBox_v1.1.1_release_yyyyMMdd.apk`）

> 声明：本软件仅用于学习和参考，不收集或上传任何数据，不参与任何订阅源的制作、收集和发布，请勿用于商业用途。

## 功能特性

- **视频点播**：多源聚合搜索、多线路切换、观看历史、收藏、最近观看
- **电视直播**：支持 `txt` / `m3u` / `m3u8` 直播源，自动分组、同频道多源切换
- **订阅导入**：支持多线路、多仓库订阅，网络地址或本地文件均可导入
- **播放器**：IJK 播放器（软解/硬解、渲染方式、画面缩放、倍速、缓存）与 EXO 播放器，支持后台播放、画中画
- **DLNA 投屏**：将影片投放到局域网内的电视/盒子等设备
- **本地播放**：播放本地视频文件（需授予存储权限）
- **EPG 节目单**：直播电子节目指南
- **其它**：无痕浏览、广告过滤、安全 DNS、数据备份与还原、自定义崩溃处理页

## 构建

### 环境要求

- JDK 11 及以上
- Android SDK：`platforms;android-33`、`platforms;android-30`、`build-tools;30.0.3`
- 本地路径配置：在项目根目录创建 `local.properties`，写入 `sdk.dir=<你的Android SDK路径>`

### 本地命令行构建

```bash
chmod +x gradlew
./gradlew assembleRelease --no-daemon
```

APK 输出到 `app/build/outputs/apk/release/`。

> 说明：`app/build.gradle` 中引用了签名文件 `TVBoxOSC.jks`（alias/密码均为 `TVBoxOSC`），该文件不随仓库提供，构建前需自行生成（`keytool -genkeypair`）或修改为你的签名配置。

### GitHub Actions 云端构建

推送代码后，在仓库 `Actions` 页面手动触发 `Test Build` workflow，构建产物可在 Artifacts 中下载。

## 说明

- 应用默认不含任何订阅源，需自行配置点播接口与直播源地址
- 播放直播/点播时若出现无法播放，可尝试切换播放器或软/硬解码方式
- 更换签名后覆盖安装旧版本会提示签名不一致，需卸载后重新安装
