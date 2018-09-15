 - Распознаватель заданной символьной цепочки. Символьная цепочка задается с помощью формул Бэкуса-Наура. Формулы Бэк-уса-Наура, определяющие нетерминальные цепочки:
 - 
           <цепочка>::=<описание константы>
           <описание константы>::=CONST<идентификатор>=<значение>;
          <идентификатор>::=<буква> | <идентификатор><буква> | < идентифика-тор ><цифра>
           <буква>::= A | B | C | D | E | F | … | Z
           <цифра>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
           <значение>::=<вещественная константа>	
          <вещественная константа>::=<целое со знаком> | <вещественное со зна-ком> | <вещественное без знака>
          <целое со знаком>::=<знак><целое без знака>
          <знак>::= + | -
          <целое без знака>::=<цифра> | <цифра><целое без знака>
          <вещественное со знаком>::=<знак><вещественное без знака>
          <вещественное без знака>::=<число с фиксированной точкой> | <число с плавающей точкой>
          <число с фиксированной точкой>::=<целая часть>.<дробная часть>
          <целая часть>::=<целое без знака>
          <дробная часть>::=пусто | <целое без знака>
          <число с плавающей точкой>::=<основание><буква порядка><порядок>
          <основание>::=пусто | <целое без знака>
          <буква порядка>::=E
          <порядок>::=<знак><целое без знака>
          
 - Терминальные символы формул выделяются полужирным шрифтом (например: $, CONST и т.д.)
Помимо этого на цепочку накладывается следующее семантическое огра-ничение: идентификатор, входящий в цепочку, не должен совпадать с ключе-выми словами языка Pascal.
 - Описание входных данных:
Цепочка записана в текстовом файле INPUT.TXT, который состоит из од-ной строки. Длина цепочки не превышает 80 символов.
 - Описание выходных данных:
Результат распознавания необходимо записать в текстовый файл OUTPUT.TXT в одно из следующих сообщений: ACCEPT, если цепочка допу-стима, и REJECT, если цепочка недопустима.
