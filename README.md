# WorkShop

<p align="center">
      <img alt="Logo" src="img/logo.png" width="100%">
</p>

<p align="center">
<img alt="Android" src="https://img.shields.io/badge/Android-39ad31">
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.9.0-A831F5">
<img alt="Jetpack Compose" src="https://img.shields.io/badge/Jetpack%20Compose-4181ED">
<img alt="Static Badge" src="https://img.shields.io/badge/Material%203-1.2.0rc01-%232310699C">
<img alt="Static Badge" src="https://img.shields.io/badge/v1.1.2-red">
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

## Список элементов в разработке
- **-** **`Отсутствуют`**

## Список элементов, которые предстоит разработать
- **-** **`RichTextField`**
- **-** **`TextField-Multi (Beta)`**

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
      implementation("com.github.RavenZIP:WorkShop:1.0.1")
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

# 📒 Инструкция
- **-** **`Отсутствует`**

# 🖼 Скриншоты
- **-** **`Отсутствуют`**

# 👾 Разработчик
**Черных Александр**
- [Github](https://github.com/RavenZIP)
- [Telegram](https://t.me/bexwdgst)
- [Telegram канал с проектами](https://t.me/RavenZIProjects)
