create table school(
    school_id integer not null primary key,
    name varchar(255),
    address varchar (255)
);

create table teacher(
    teacher_id integer not null primary key,
    surname varchar(255),
    name varchar(255),
    patronymic varchar(255),
    age integer,
    sex varchar(10),
    school_id integer,
    foreign key (school_id) references school(school_id),
);

create table lesson(
    lesson_id integer not null primary key,
    name varchar(255)
);

create table student(
    student_id integer not null primary key,
    surname varchar(255),
    name varchar(255),
    patronymic varchar(255),
    age integer  not null,
    sex varchar(10),
    school_id integer,
    foreign key (school_id) references school(school_id)
);

create table student_lesson(
    lesson_id integer,
    student_id,
    foreign key (lesson_id) references lesson (lesson_id),
    foreign key (student_id) references student (student_id)
);

create table teacher_lesson(
    lesson_id integer,
    teacher_id integer,
    foreign key (lesson_id) references lesson (lesson_id),
    foreign key (teacher_id) references teacher (teacher_id),
);