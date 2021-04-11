Список параметров:

**browser** - тип браузера. Если не задано, то хром

**browser.screen.size** - размер окна браузера. Если не задано, открывает на полный экран

**remote.web.driver** - путь до селеноида

**video.storage** - если задано, записывает видео. Должен содержать путь к хранилищу видео файлов



Пример запуска

```gradle clean positive_tests -Dbrowser=chrome -Dremote.driver="https://%s:%s@$selenoid.autotests.cloud/wd/hub/" -Dvideo.storage="https://selenoid.autotests.cloud/video/" ```
