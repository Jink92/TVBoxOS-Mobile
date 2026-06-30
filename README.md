# TVBoxOS-Mobile

基于 [TVBoxOSC](https://github.com/CatVodTVOfficial/TVBoxOSC) 和 [TVBoxOS](https://github.com/q215613905/TVBoxOS) 开发，专为手机竖屏优化的 TVBox 版本。

## 特色

- 📱 **手机竖屏适配** - 底部导航栏，舒适的手机操作体验
- 🔍 **快速搜索** - 支持分词搜索、跨源搜索
- 🎬 **多播放器支持** - 集成 ExoPlayer、IJKPlayer 等
- 📺 **直播/点播** - 支持直播源订阅和视频点播
- 📖 **收藏/历史** - 本地缓存观看记录和收藏
- ⚙️ **配置灵活** - 支持自定义订阅、解析、播放设置

## 截图

| 首页 | 详情 | 我的 |
|------|------|------|
| ![首页](https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=A%20modern%20mobile%20video%20app%20home%20page%20with%20search%20bar%20at%20top%2C%20category%20tabs%20and%20video%20grid%20list%20showing%20movie%20posters%20in%20a%20phone%20screen&image_size=portrait_4_3) | ![详情](https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=A%20mobile%20video%20detail%20page%20showing%20movie%20cover%2C%20title%2C%20description%20and%20episode%20list%20on%20a%20phone%20screen&image_size=portrait_4_3) | ![我的](https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=A%20mobile%20user%20profile%20page%20with%20menu%20list%20items%20like%20history%2C%20favorites%2C%20subscriptions%20and%20settings%20in%20a%20phone%20screen&image_size=portrait_4_3) |

## 下载

前往 [Releases](https://github.com/Jink92/TVBoxOS-Mobile/releases) 下载最新版本 APK。

## 开始使用

1. 安装 APK
2. 进入 **订阅管理** 添加你的订阅地址
3. 返回首页即可浏览和搜索内容

## 构建

### 使用 GitHub Actions（推荐）

Fork 本仓库，在 Actions 页面选择 **Build APK** 工作流，手动触发构建。

### 本地构建

```bash
# Linux/macOS
./gradlew assembleJavaDebug

# Windows
gradlew.bat assembleJavaDebug
```

## 技术栈

- **语言**: Java + Kotlin
- **UI**: Material Design、ViewBinding、DslTabLayout
- **播放器**: ExoPlayer、DKPlayer
- **图片加载**: Glide
- **网络请求**: OkGo、OkHttp
- **数据存储**: Room、Hawk
- **弹窗**: XPopup
- **状态栏**: ImmersionBar

## 致谢

- [CatVodTVOfficial/TVBoxOSC](https://github.com/CatVodTVOfficial/TVBoxOSC)
- [q215613905/TVBoxOS](https://github.com/q215613905/TVBoxOS)
- [mlcs-top/TVBoxOS-Mobile](https://github.com/mlcs-top/TVBoxOS-Mobile)
- [kukuqi666/TVBoxOS-Mobile](https://github.com/kukuqi666/TVBoxOS-Mobile)
- [doikki/DKVideoPlayer](https://github.com/doikki/DKVideoPlayer)

## 声明

- 本软件仅供学习参考，请于安装后 24 小时内删除
- 本软件只提供展示功能，不收集或上传任何数据
- 本软件不参与任何订阅或源的制作和发布
- 开发者不对任何订阅内容负责

## License

```
MIT License

Copyright (c) 2024 Jink92

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files...
```
