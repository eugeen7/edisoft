DELETE FROM IDGEN;
DELETE FROM CRBODY CASCADE;
DELETE FROM MOTOR CASCADE;
DELETE FROM TRNSMSN CASCADE;
DELETE FROM AUTOMBL CASCADE;

Insert into IDGEN (IDNAME,IDVAL) values ('atfctr_pk',1);
Insert into IDGEN (IDNAME,IDVAL) values ('atmbl_pk',10);
Insert into IDGEN (IDNAME,IDVAL) values ('bdclrs_pk',10);
Insert into IDGEN (IDNAME,IDVAL) values ('bdtype_pk',10);
Insert into IDGEN (IDNAME,IDVAL) values ('crbody_pk',10);
Insert into IDGEN (IDNAME,IDVAL) values ('motor_pk',8);
Insert into IDGEN (IDNAME,IDVAL) values ('mtrtype_pk',5);
Insert into IDGEN (IDNAME,IDVAL) values ('trnsmsn_pk',6);
Insert into IDGEN (IDNAME,IDVAL) values ('trnstype_pk',4);

Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (1,1,'Седан','Седан наиболее распространённый тип кузова, может быть двух- или четырёхдверным');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (2,1,'Универсал','Универсал - тип закрытого двухобъёмного грузо-пассажирского кузова легкового автомобиля');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (3,1,'Хэтчбэк','Хэтчбэк: двухобъёмный грузо-пассажирский кузов, с тремя или пятью дверьми');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (4,1,'Купе','Купе: двухдверный трёхобъёмный кузов, с одним рядом сидений, либо с задним сиденьем ограниченной вместимости');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (5,1,'Лимузин','Лимузин: закрытый кузов легкового автомобиля высшего класса на основе седана');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (6,1,'Микроавтобус','Микроавтобус');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (7,1,'Минивэн','Минивэн: промежуточный вариант между универсалом и микроавтобусом');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (8,1,'Лифтбэк','Лифтбэк: хэтчбэк с длинным, как у седана, задним свесом');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (9,1,'Кабриолет','Кабриолет: открытый автомобильный кузов, двух- или четырёхдверный');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (10,1,'Родстер','Родстер: двухместный кузов со складываемым мягким верхом');

Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (1,1,'Белый','Белый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (2,1,'Золотистый','Золотистый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (3,1,'Голубой','Голубой цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (4,1,'Зеленый','Зеленый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (5,1,'Коричневый','Коричневый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (6,1,'Красный','Красный цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (7,1,'Синий','Синий цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (8,1,'Серый','Серый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (9,1,'Серебристый','Серебристый цвет');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (10,1,'Черный','Черный цвет');

Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (1,1,'Карбюраторный двигатель','Особенностью является получение топливо-бензиновой смеси в специальном смесителе, карбюраторе');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (2,1,'Инжекторный двигатель','Особенностью является получение топливной смеси в коллекторе или цилиндрах двигателя путём подачи инжекторной системой подачи топлива');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (3,1,'Роторно-поршневой','Основа двигателя — треугольный ротор (поршень), вращающийся в камере особой 8-образной формы');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (4,1,'Дизельный двигатель','Поршневой двигатель внутреннего сгорания, работающий по принципу самовоспламенения распылённого топлива от воздействия разогретого при сжатии воздуха');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (5,1,'Газодизельный двигатель','Двигатель внутреннего сгорания, сконструированный на основе дизельного двигателя (или переделанный из дизельного двигателя), топливом в котором является природный газ (метан) или сжиженные углеводородные газы');

Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (1,1,'Механическая','каиболее распространенный вид передачи энергии, присутствует во всех автомобил¤х с механической коробкой переключени¤ передач и блоком сцепления');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (2,1,'Гидромеханическая','Механическое вращение трансформируетс¤ в движение жидкости (масла) в гидротрансформаторе более совершенной замене сцепления. Передача крутящего момента через трансформатор более плавная.');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (3,1,'Электрическая','используетс¤ в электрокарах и машинах с гибридными силовыми установками');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (4,1,'Гибридная','Гибридна¤ трансмисси¤ последовательного типа позвол¤ет использовать очень малой мощности, а в режиме город при езде на минимальной скорости и с частыми остановками возможно отключить полностью');

Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (1,1,1,1,4,'CRBD-000-000-001','Белый седан',1,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (2,2,2,1,5,'CRBD-000-000-002','Золотистый универсал',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (3,3,3,1,4,'CRBD-000-000-003','Голубой хэтчбэк',3,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (4,4,4,1,2,'CRBD-000-000-004','Зеленое купе',4,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (5,5,5,1,6,'CRBD-000-000-005','Коричневый лимузин',5,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (6,6,6,1,4,'CRBD-000-000-006','Красный микроавтобус',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (7,7,7,1,4,'CRBD-000-000-007','Синий минивэн',7,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (8,8,8,1,5,'CRBD-000-000-008','Серый лифтбэк',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (9,9,9,1,2,'CRBD-000-000-009','Серебристый кабриолет',9,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (10,10,10,1,2,'CRBD-000-000-010','Черный родстер',10,TRUE);

Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (1,1,1,1500,70,'MTR-000-000-001','Карбюраторный 4 целиндровый двигатель 1500 см3',1,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (2,1,1,2000,100,'MTR-000-000-002','Карбюраторный 4 целиндровый двигатель 2000 см3',2,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (3,2,1,1600,120,'MTR-000-000-003','Инжекторный 4 целиндровый двигатель двигатель1600 см3',null,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (4,2,1,2500,200,'MTR-000-000-004','Инжекторный 6 целиндровый двигатель двигатель 2500 см3',4,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (5,3,1,1500,150,'MTR-000-000-005','Роторно-поршневой 1500 см3',5,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (6,4,1,3000,250,'MTR-000-000-006','Дизельный двигатель V6 3000',6,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (7,4,1,2000,170,'MTR-000-000-007','Дизельный двигатель рядный 6 целиндров 2000',7,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (8,5,1,3000,100,'MTR-000-000-008','Газодизельный 4 целиндровый двигатель',null,TRUE);

Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (1,1,1,'TRNS-OO0-000-001','4 ступенчатая Механника',1,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (2,1,1,'TRNS-OO0-000-002','5 ступенчатая Механника',2,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (3,2,1,'TRNS-OO0-000-003','5 ступенчатая Гидромеханника',3,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (4,2,1,'TRNS-OO0-000-004','6 ступенчатая Гидромеханника',4,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (5,3,1,'TRNS-OO0-000-005','Электрическая',null,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (6,3,1,'TRNS-OO0-000-006','Гибридная',null,TRUE);

Insert into AUTOFCTR (AUTOFCTR_ID,VERSNUM,FCTR_NM,ADRS,AUTODSC) values (1,1,'АвтоФорд','Россия, Москва, ул. Гоголя 10','Автомобильный завод');

Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (1,1,1,1,1,1,'Форд 2101','Форд 2101',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (2,1,null,2,2,1,'Форд 2102','Форд 2102',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (3,1,3,null,3,1,'Форд 2103','Форд 2103',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (4,1,4,4,4,1,'Форд 2104','Форд 2104',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (5,1,5,5,null,1,'Форд 2105','Форд 2105',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (6,1,null,6,null,1,'Форд 2106','Форд 2106',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (7,1,7,7,null,1,'Форд 2107','Форд 2107',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (8,1,null,null,null,1,'Форд 2108','Форд 2108',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (9,1,9,null,null,1,'Форд 2109','Форд 2109',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (10,1,10,null,null,1,'Форд 2110','Форд 2110',TRUE);