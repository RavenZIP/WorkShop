# WorkShop

<p align="center">
      <img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/08bd7c22-ff64-437c-a222-4aac5f3c5fa5" width="100%">
</p>

<p align="center">
<img alt="Android" src="https://img.shields.io/badge/Android-39ad31">
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.0.0-A831F5">
<img alt="Jetpack Compose" src="https://img.shields.io/badge/Jetpack%20Compose-4181ED">
<img alt="Static Badge" src="https://img.shields.io/badge/Material%203-1.2.1-%232310699C">
<img alt="Static Badge" src="https://img.shields.io/badge/v1.8.0-red">
</p>

# 📄 О проекте
## Описание
Данная библиотека создана с целью упрощения разработки мобильных приложений с использованием связки Jetpack Compose + Kotlin. Вы можете использовать ее напрямую в своих проектах или же просто посмотреть реализацию того или иного элемента.

## Список готовых элементов
- **-** **`Button`**
- **-** **`TextField`**
- **-** **`Switch`**
- **-** **`RadioButton`**
- **-** **`CheckBox`**
- **-** **`Card`**
- **-** **`BottomBar`**
- **-** **`TopBar`**
- **-** **`DialogWindow`**
- **-** **`Spinner`**
- **-** **`SnackBar`**
- **-** **`PagerIndicator`**
- **-** **`SearchBar`**

## Список готовых контейнеров
- **-** **`VerticalGrid`**

# 🛠 Установка
В файл **settings.gradle.kts** добавьте
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ("https://jitpack.io")
    }
}
```
Затем, в файл **build.gradle.kts (:app)** добавьте
```
dependencies {
      implementation("com.github.RavenZIP:WorkShop:1.8.0")
}
```
Обратите внимание, что minSdk должен быть указан >= 27
```
android {
      defaultConfig {
            minSdk = 27
      }
}
```

Синхронизируйте Gradle с проектом и запустите сборку.

# 💡 Устранение возможной (❗) проблемы с отображением исходников в коде
1. Скачать sources.jar той же версии, что и версия библиотеки в вашем проекте
```
https://jitpack.io/com/github/RavenZIP/WorkShop/1.8.0/WorkShop-1.8.0-sources.jar
```
2. Перейти в реализацию одного из методов, который есть в библиотеке. Сверху появятся три кнопки (Decompile to Java, Download Sources, Choose Sources)

![image](https://github.com/RavenZIP/WorkShop/assets/131264945/dabd76cb-154d-443a-a256-5092e40494f6)


3. Нажать на Choose Sources и указать путь до скачанного sources.jar

# 🚬 Использование
- **-** **`Описание отсутствует`**

# 🖼 Скриншоты
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/12dd43e5-39e3-4215-a587-60c65e31d2bb" width="30%" height="30%">
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/e55d8b4f-8b70-41e4-bb02-63bfd319d0a2" width="30%" height="30%">
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/59cf9ef1-dfb8-4c53-bc0c-d38827fc799f" width="30%" height="30%">
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/a14dfdc8-3c4d-486f-8306-5e419cc1611f" width="30%" height="30%">
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/a843d6bc-7ac0-41d8-acde-6948b0e1e442" width="30%" height="30%">
<img alt="Logo" src="https://github.com/RavenZIP/WorkShop/assets/131264945/bdf06836-8481-4462-a24a-6cd6bb609a6f" width="30%" height="30%">


# 👾 Разработчик
**Черных Александр**
- [Github](https://github.com/RavenZIP)
- [Telegram](https://t.me/bexwdgst)
- [Telegram канал с проектами](https://t.me/RavenZIProjects)
