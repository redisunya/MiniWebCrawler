# MiniWebCrawler
Мини веб-краулер для поиска информации о пользователе gitHub

Комментарии по решению задания:

Проведена декомпозиция проекта на компоненты, в результате которой пришёл к выводу, что оптимально будет написать 4 класса:
Person, Repository, Repositories и собственно сам Crawler. Каждый из которых отвечает за свою часть решаемой задачи.
В рамках классов также была проведена декомпозиция на отдельные методы.

1. Все необходимые поля загружаются
2. Переменные среды(мне правда не очевидно для чего они могли бы пригодиться) ищутся и вполне могут быть использованы
3. Относительно моего тестирования результаты выдавались приемлимые, однако спустя какоето время 
   API начал ругаться на частоту запросов и закрывать мне доступ с моего IP


Задание
Необходимо написать мини web-crawler для сайта github.com

На вход программе подаётся файл, в котором будут урлы github профилей

Для каждого профиля P из файла необходимо определить

-ник
-полное имя
-компанию
-локацию
-язык программирования, на котором чаще всего пишет P (наиболее часто встречается среди репозиториев)
-название самого популярного репозитория
-кол-во stars в этом репозитории
-основной язык, на котором написан этот репозиторий
Для получения всех этих данных использовать API сайта github.com

Ограничения:

Кол-во одновременных запросов, которые может делать web-crawler берётся из переменной окружения crawler.maxParallelRequests, по умолчанию 1
Задержка между последовательными запросами берётся из переменной окружения crawler.requestDelayMs
При оценивании задания основное внимание будет уделяться правильной декомпозиции системы на компоненты, следование ООП стандартам, чистоте кода и т.п. 
Постарайтесь написать код так, чтобы в него легко можно было добавить скачивание других сайтов, не передывая всю архитектуру.
