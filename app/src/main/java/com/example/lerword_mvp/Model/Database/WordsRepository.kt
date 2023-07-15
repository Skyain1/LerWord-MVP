package com.example.lerword.Model.Database

import com.example.lerword_mvp.Model.Word

/*
 * Created by Skyain1 on 24.06.2023.
 */

//Имитация базы данных

class WordsRepository {
    private val words = ArrayList <Word>()

    init {
        words.add(Word(1,"to meet","Глаголы","встречать, соответствовать, удовлетворять",0))
        words.add(Word(2,"able","Прилагательные","способный, умеющий, компетентный",0))
        words.add(Word(3,"bad","Прилагательные","плохой, вредный, злой",0))
        words.add(Word(4,"best","Прилагательные","лучший",0))
        words.add(Word(5,"big","Прилагательные","большой",0))
        words.add(Word(6,"black","Прилагательные","черный",0))
        words.add(Word(7,"clear","Прилагательные","ясный, чистый, понятный",0))
        words.add(Word(8,"different","Прилагательные","непохожий, другой, отличный, разный",0))
        words.add(Word(9,"early","Прилагательные","ранний",0))
        words.add(Word(10,"easy","Прилагательные","легкий",0))
        words.add(Word(11,"economic","Прилагательные","экономический",0))
        words.add(Word(12,"free","Прилагательные","свободный, бесплатный",0))
        words.add(Word(13,"full","Прилагательные","наполненный, целый, сытый",0))
        words.add(Word(14,"cold day ","Дни","холодный день",0))
        words.add(Word(15,"nice day ","Дни","ясный, хороший денёк",0))
        words.add(Word(16,"foggy day ","Дни","туманный день",0))
        words.add(Word(17,"gloomy day ","Дни","хмурый день",0))
        words.add(Word(18,"stifling day","Дни","душный день",0))
        words.add(Word(19,"rainy day","Дни","дождливый день",0))
        words.add(Word(20,"sunny day","Дни","солнечный день",0))
        words.add(Word(21,"warm day","Дни","тёплый день",0))
        words.add(Word(22,"day after day ","Дни","изо дня в день",0))
        words.add(Word(23,"now","Наречия времени","сейчас",0))
        words.add(Word(24,"then","Наречия времени","затем",0))
        words.add(Word(25,"never","Наречия времени","никогда",0))
        words.add(Word(26,"again","Наречия времени","снова",0))
        words.add(Word(27,"always","Наречия времени","всегда",0))
        words.add(Word(28,"today","Наречия времени","сегодня",0))
        words.add(Word(29,"long","Наречия времени","долго",0))
        words.add(Word(30,"ever","Наречия времени","вечно",0))
        words.add(Word(31,"later","Наречия времени","позже",0))
        words.add(Word(32,"early","Наречия времени","рано",0))
        words.add(Word(33,"Omelette ","Блюда","Омлет",0))
        words.add(Word(34,"Cutlet","Блюда","Отбивная",0))
        words.add(Word(35,"(Beef) steak","Блюда","Бифштекс",0))
        words.add(Word(36,"Pancake","Блюда","Блин",0))
        words.add(Word(37,"Meat dumplings","Блюда","Пельмени",0))
        words.add(Word(38,"Borsch","Блюда","Борщ",0))
        words.add(Word(39,"Pie","Блюда","Пирог",0))
        words.add(Word(40,"Pizza ","Блюда","Пицца",0))
        words.add(Word(41,"Pilau, pilaf","Блюда","Плов",0))
        words.add(Word(42,"Bouillon ","Блюда","Бульон",0))
        words.add(Word(43,"Sandwich ","Блюда","Бутерброд",0))
        words.add(Word(44,"Pudding ","Блюда","Пудинг",0))
        words.add(Word(45,"Preserve ","Блюда","Варенье",0))
        words.add(Word(46,"Vermicelli ","Блюда","Вермишель",0))
        words.add(Word(47,"Beetroot salad","Блюда","Винегрет",0))
        words.add(Word(48,"Hamburger ","Блюда","Гамбургер",0))
        words.add(Word(49,"Circle  ","Фигуры","круг",0))
        words.add(Word(50,"Cone  ","Фигуры","конус",0))
        words.add(Word(51,"Cylinder ","Фигуры","цилиндр",0))
        words.add(Word(52,"Ellipse","Фигуры","эллипс",0))
        words.add(Word(53,"Hexagon ","Фигуры","шестиугольник",0))
        words.add(Word(54,"Irregular ","Фигуры","неправильная форма",0))
        words.add(Word(55,"Octagon","Фигуры","восьмиугольник",0))
        words.add(Word(56,"Parallelogram","Фигуры","параллелограмм",0))
        words.add(Word(57,"Pentagon ","Фигуры","пятиугольник",0))
        words.add(Word(58,"Pyramid","Фигуры","пирамида",0))
        words.add(Word(59,"Rectangle ","Фигуры","прямоугольник",0))
        words.add(Word(60,"Semicircle ","Фигуры","полукруг",0))
        words.add(Word(61,"Sphere","Фигуры","сфера",0))
        words.add(Word(62,"Square ","Фигуры","квадрат",0))
        words.add(Word(63,"Star ","Фигуры","звезда",0))
        words.add(Word(64,"Wedge ","Фигуры","клин",0))
    }

    suspend fun getWords() =words
    suspend fun updateField(word: Word){
        words[word.Id-1].CheckCount++
    }
    suspend fun setKnow(word: Word){
        words[word.Id-1].know = true
    }

}