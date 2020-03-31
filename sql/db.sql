--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-03-24 15:19:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 16607)
-- Name: Order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Order" (
    id integer NOT NULL,
    studentyear_id integer,
    reason_id integer,
    date_of_order integer NOT NULL
);


ALTER TABLE public."Order" OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16605)
-- Name: Order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Order_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Order_id_seq" OWNER TO postgres;

--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 210
-- Name: Order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Order_id_seq" OWNED BY public."Order".id;


--
-- TOC entry 207 (class 1259 OID 16571)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    role integer NOT NULL,
    CONSTRAINT "User_role_check" CHECK ((role = ANY (ARRAY[0, 1])))
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16569)
-- Name: User_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_id_seq" OWNER TO postgres;

--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 206
-- Name: User_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_id_seq" OWNED BY public."User".id;


--
-- TOC entry 213 (class 1259 OID 16625)
-- Name: orderdoc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderdoc (
    id integer NOT NULL,
    order_id integer,
    reasondoc_id integer,
    isaccepted boolean DEFAULT false
);


ALTER TABLE public.orderdoc OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16623)
-- Name: orderdoc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderdoc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orderdoc_id_seq OWNER TO postgres;

--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 212
-- Name: orderdoc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orderdoc_id_seq OWNED BY public.orderdoc.id;


--
-- TOC entry 217 (class 1259 OID 16666)
-- Name: reason; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reason (
    id integer NOT NULL,
    number numeric NOT NULL,
    note text NOT NULL,
    max_coef integer NOT NULL
);


ALTER TABLE public.reason OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16664)
-- Name: reason_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reason_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reason_id_seq OWNER TO postgres;

--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 216
-- Name: reason_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reason_id_seq OWNED BY public.reason.id;


--
-- TOC entry 209 (class 1259 OID 16596)
-- Name: reasondoc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reasondoc (
    id integer NOT NULL,
    number numeric NOT NULL,
    doc_name text NOT NULL
);


ALTER TABLE public.reasondoc OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16594)
-- Name: reasondoc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reasondoc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reasondoc_id_seq OWNER TO postgres;

--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 208
-- Name: reasondoc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reasondoc_id_seq OWNED BY public.reasondoc.id;


--
-- TOC entry 203 (class 1259 OID 16544)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id integer NOT NULL,
    last_name text NOT NULL,
    first_name text NOT NULL,
    patronymic text NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16542)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_seq OWNER TO postgres;

--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 202
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;


--
-- TOC entry 215 (class 1259 OID 16644)
-- Name: studentadditional; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.studentadditional (
    id integer NOT NULL,
    studentyear_id integer,
    sum numeric NOT NULL,
    reason_id integer,
    month integer NOT NULL,
    isproved boolean DEFAULT false,
    date_of_prove integer
);


ALTER TABLE public.studentadditional OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16642)
-- Name: studentadditional_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.studentadditional_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.studentadditional_id_seq OWNER TO postgres;

--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 214
-- Name: studentadditional_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.studentadditional_id_seq OWNED BY public.studentadditional.id;


--
-- TOC entry 205 (class 1259 OID 16555)
-- Name: studentyear; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.studentyear (
    id integer NOT NULL,
    course integer NOT NULL,
    "group" integer NOT NULL,
    isfreeibe boolean DEFAULT true,
    student_id integer,
    year integer NOT NULL
);


ALTER TABLE public.studentyear OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16553)
-- Name: studentyear_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.studentyear_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.studentyear_id_seq OWNER TO postgres;

--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 204
-- Name: studentyear_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.studentyear_id_seq OWNED BY public.studentyear.id;


--
-- TOC entry 2740 (class 2604 OID 16610)
-- Name: Order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Order" ALTER COLUMN id SET DEFAULT nextval('public."Order_id_seq"'::regclass);


--
-- TOC entry 2737 (class 2604 OID 16574)
-- Name: User id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User" ALTER COLUMN id SET DEFAULT nextval('public."User_id_seq"'::regclass);


--
-- TOC entry 2741 (class 2604 OID 16628)
-- Name: orderdoc id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdoc ALTER COLUMN id SET DEFAULT nextval('public.orderdoc_id_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 16669)
-- Name: reason id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reason ALTER COLUMN id SET DEFAULT nextval('public.reason_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 16599)
-- Name: reasondoc id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reasondoc ALTER COLUMN id SET DEFAULT nextval('public.reasondoc_id_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 16547)
-- Name: student id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);


--
-- TOC entry 2743 (class 2604 OID 16647)
-- Name: studentadditional id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentadditional ALTER COLUMN id SET DEFAULT nextval('public.studentadditional_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 16558)
-- Name: studentyear id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentyear ALTER COLUMN id SET DEFAULT nextval('public.studentyear_id_seq'::regclass);


--
-- TOC entry 2906 (class 0 OID 16607)
-- Dependencies: 211
-- Data for Name: Order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Order" (id, studentyear_id, reason_id, date_of_order) FROM stdin;
1	1	1	15022012
\.


--
-- TOC entry 2902 (class 0 OID 16571)
-- Dependencies: 207
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."User" (id, login, password, role) FROM stdin;
\.


--
-- TOC entry 2908 (class 0 OID 16625)
-- Dependencies: 213
-- Data for Name: orderdoc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderdoc (id, order_id, reasondoc_id, isaccepted) FROM stdin;
1	1	1	t
2	1	1	f
\.


--
-- TOC entry 2912 (class 0 OID 16666)
-- Dependencies: 217
-- Data for Name: reason; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reason (id, number, note, max_coef) FROM stdin;
1	17.1	Пострадавшим при стихийных бедствиях, пожаре, хищении, краже личного имущества и других непредвиденных обстоятельствах, резко ухудшающих материальное положение	20
2	17.2	Длительное лечение, в том числе стационарное, не менее 21 календарного дня	10
3	17.3	Платное медобслуживание, медикаментозное лечение, рекомендованное государственными учреждениями здравоохранения (кроме стоматологических услуг, санаторно-курортного лечения и оздоровления)	10
4	17.4	Имеющим группу инвалидности: III группа инвалидности II группа инвалидности I группа инвалидности, ребенок-инвалид (до достижения им возраста 18 лет)	5
5	17.5	Воспитывающим детей	4
6	17.6	Из многодетных семей 	10
7	17.7	Из неполных семей 	10
8	17.8	Имеющим тяжелое материальное положение	15
\.


--
-- TOC entry 2904 (class 0 OID 16596)
-- Dependencies: 209
-- Data for Name: reasondoc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reasondoc (id, number, doc_name) FROM stdin;
1	17.1	Личное заявление студента БГУ
2	17.1	Документы, подтверждающие наличие чрезвычайных обстоятельств и стихийных бедствий (справка из милиции или МЧС и др.)
3	17.2	Личное заявление студента БГУ
4	17.2	Копия справки по болезни, больничного листа (за исключением больничного листа по беременности и родам)
5	17.3	Личное заявление студента БГУ
6	17.3	Выписка из медицинских документов либо справка государственного учреждения здравоохранения о назначении необходимого лечения
7	17.3	Копия рецепта врача о назначении лекарственных средств
8	17.3	Чеки, копия чеков на покупку лекарств, оплату медицинских услуг
9	17.4	Личное заявление студента БГУ
10	17.4	Копия удостоверения инвалида, заключение МРЭК
11	17.5	Личное заявление студента БГУ
12	17.5	Копия свидетельства о рождении ребенка
13	17.6	Личное заявление студента БГУ
14	17.6	Копия удостоверения многодетной семьи
15	17.6	Справка (брата/сестры) об обучении в УВО, УССО, УПТО на дневной форме получения образования
16	17.7	Личное заявление студента БГУ
17	17.7	Копия документа, удостоверяющего личность оставшегося родителя (паспорт, вид на жительство, удостоверение беженца)
18	17.7	Копии документов, подтверждающих статус неполной семьи (справка из ЗАГСа, копия свидетельства о смерти родителя, копия свидетельства об усыновлении, копия решение суда о признании родителя безвестно отсутствующим либо о лишении родительских прав)
19	17.7	Справку о месте жительства и составе семьи студента, а так же членов его семьи (в случае, если члены семьи не зарегистрированы по месту жительства студента)
20	17.8	Личное заявление студента БГУ
21	17.8	Справка о месте жительства и составе семьи, а так же членов его семьи (в случае, если члены семьи не зарегистрированы по месту жительства студента)
22	17.8	Документы о доходах (заработной плате, пенсии, пособии, стипендии, алиментах и др. доходах) каждого члена семьи за последние двенадцать месяцев на момент подачи заявления либо предоставление документов, подтверждающих отсутствие доходов (копия трудовой книжки) 
23	17.8	Справки о других членах семьи (учащихся учебных заведений), находящихся на иждивении родителей
24	17.8	При отсутствии одного из родителей – копия свидетельства о смерти, копия решения суда о расторжении брака, справка из ЗАГСа и др.
25	17.8	Для студента, состоящего в браке – копия свидетельства о заключении брака
26	17.8	Для студента, имеющего детей – копия свидетельства о рождении ребенка
\.


--
-- TOC entry 2898 (class 0 OID 16544)
-- Dependencies: 203
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, last_name, first_name, patronymic) FROM stdin;
7	fgh	fgh	fgh
9	qwe	qwe	we
10	qweqwe	qweqwe	qweqwe
8	qwe123	qwe123	qwe123
11	asd	asd	asd
\.


--
-- TOC entry 2910 (class 0 OID 16644)
-- Dependencies: 215
-- Data for Name: studentadditional; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.studentadditional (id, studentyear_id, sum, reason_id, month, isproved, date_of_prove) FROM stdin;
\.


--
-- TOC entry 2900 (class 0 OID 16555)
-- Dependencies: 205
-- Data for Name: studentyear; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.studentyear (id, course, "group", isfreeibe, student_id, year) FROM stdin;
1	2	2	t	7	2012
5	4	4	t	11	2015
4	6	4	t	10	2000
\.


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 210
-- Name: Order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Order_id_seq"', 1, true);


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 206
-- Name: User_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_id_seq"', 1, false);


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 212
-- Name: orderdoc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderdoc_id_seq', 2, true);


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 216
-- Name: reason_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reason_id_seq', 8, true);


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 208
-- Name: reasondoc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reasondoc_id_seq', 26, true);


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 202
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_id_seq', 11, true);


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 214
-- Name: studentadditional_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.studentadditional_id_seq', 2, true);


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 204
-- Name: studentyear_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.studentyear_id_seq', 5, true);


--
-- TOC entry 2759 (class 2606 OID 16612)
-- Name: Order Order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY (id);


--
-- TOC entry 2753 (class 2606 OID 16582)
-- Name: User User_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_login_key" UNIQUE (login);


--
-- TOC entry 2755 (class 2606 OID 16580)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- TOC entry 2761 (class 2606 OID 16631)
-- Name: orderdoc orderdoc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdoc
    ADD CONSTRAINT orderdoc_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 16674)
-- Name: reason reason_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_pkey PRIMARY KEY (id);


--
-- TOC entry 2757 (class 2606 OID 16604)
-- Name: reasondoc reasondoc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reasondoc
    ADD CONSTRAINT reasondoc_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 16552)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 2763 (class 2606 OID 16653)
-- Name: studentadditional studentadditional_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentadditional
    ADD CONSTRAINT studentadditional_pkey PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 16561)
-- Name: studentyear studentyear_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentyear
    ADD CONSTRAINT studentyear_pkey PRIMARY KEY (id);


--
-- TOC entry 2751 (class 2606 OID 16563)
-- Name: studentyear studentyear_student_id_year_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentyear
    ADD CONSTRAINT studentyear_student_id_year_key UNIQUE (student_id, year);


--
-- TOC entry 2767 (class 2606 OID 16613)
-- Name: Order Order_studentyear_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_studentyear_id_fkey" FOREIGN KEY (studentyear_id) REFERENCES public.studentyear(id);


--
-- TOC entry 2768 (class 2606 OID 16632)
-- Name: orderdoc orderdoc_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdoc
    ADD CONSTRAINT orderdoc_order_id_fkey FOREIGN KEY (order_id) REFERENCES public."Order"(id);


--
-- TOC entry 2769 (class 2606 OID 16637)
-- Name: orderdoc orderdoc_reasondoc_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdoc
    ADD CONSTRAINT orderdoc_reasondoc_id_fkey FOREIGN KEY (reasondoc_id) REFERENCES public.reasondoc(id);


--
-- TOC entry 2770 (class 2606 OID 16654)
-- Name: studentadditional studentadditional_studentyear_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentadditional
    ADD CONSTRAINT studentadditional_studentyear_id_fkey FOREIGN KEY (studentyear_id) REFERENCES public.studentyear(id);


--
-- TOC entry 2766 (class 2606 OID 16564)
-- Name: studentyear studentyear_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studentyear
    ADD CONSTRAINT studentyear_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.student(id);


-- Completed on 2020-03-24 15:19:42

--
-- PostgreSQL database dump complete
--

