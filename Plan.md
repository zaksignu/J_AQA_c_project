
#1. сценарии

##1.1 happypath
Корректное значение в полях, карта с номером АPPROVED, выполнение покупки
##1.2 ui tests
Проверка реакции на вводимые значения в полях, принимая, что изначально все поля заполнены валидными значениями:

- +поле номера карты: неполный номер
- +поле ввода месяца: одна цифра ( 8 а не 08 к примеру), цифра больше 12
- поле ввода года, одна цифра, 00, 99, цифра меньше текущего года
- поле ввода имени: на русском, без пробела, один символ, цифра, заведомо большое имя
- поле ввода CVC  кода - неполный код, отсутствие ввода цифр
##1.3.DB tests

корректные значения в форме, карта

еправильныйй номер карты
неправильный месяц
неправильный год
некорректный владелец
неправильный номер карты
1.3 mssql tests






Планирование
После начала работы над проектом в течение 3 рабочих дней вы должны сдать наставнику план автоматизации, в котором описано:

Перечень автоматизируемых сценариев
Перечень используемых инструментов с обоснованием выбора
Перечень и описание возможных рисков при автоматизации
Интервальная оценка с учётом рисков (в часах)
План сдачи работ (когда будут автотесты, результаты их прогона и отчёт по автоматизации)