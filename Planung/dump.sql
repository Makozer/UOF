PGDMP     !    5                x           1920-Galaxywars    9.5.19    12.2 "    u           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            v           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            w           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            x           1262    83469    1920-Galaxywars    DATABASE     �   CREATE DATABASE "1920-Galaxywars" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
 !   DROP DATABASE "1920-Galaxywars";
                1920-Galaxywars    false            y           0    0    DATABASE "1920-Galaxywars"    ACL     �   REVOKE ALL ON DATABASE "1920-Galaxywars" FROM PUBLIC;
REVOKE ALL ON DATABASE "1920-Galaxywars" FROM "1920-Galaxywars";
GRANT ALL ON DATABASE "1920-Galaxywars" TO "1920-Galaxywars";
                   1920-Galaxywars    false    2168            z           0    0    1920-Galaxywars    DATABASE PROPERTIES     S   ALTER ROLE "1920-Galaxywars" IN DATABASE "1920-Galaxywars" SET "TimeZone" TO '+1';
                     1920-Galaxywars    false            {           0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    206685    eventid_seq    SEQUENCE     t   CREATE SEQUENCE public.eventid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.eventid_seq;
       public          1920-Galaxywars    false            �            1259    591532    events    TABLE     �  CREATE TABLE public.events (
    eventid bigint DEFAULT nextval('public.eventid_seq'::regclass) NOT NULL,
    typ character varying(10) NOT NULL,
    thisgalaxy integer NOT NULL,
    thissolarsystem integer NOT NULL,
    thisplanet integer NOT NULL,
    targetgalaxy integer,
    targetsolarsystem integer,
    targetplanet integer,
    building character varying,
    fleet character varying(333),
    ressource character varying,
    starttime timestamp with time zone DEFAULT timezone('Europe/Berlin'::text, now()) NOT NULL,
    endttime timestamp with time zone NOT NULL,
    userid integer NOT NULL,
    targetuserid integer,
    lock character varying DEFAULT ''::character varying NOT NULL
);
    DROP TABLE public.events;
       public            1920-Galaxywars    false    181            �            1259    591073    messageid_seq    SEQUENCE     �   CREATE SEQUENCE public.messageid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9222222036664771337
    CACHE 1;
 $   DROP SEQUENCE public.messageid_seq;
       public          1920-Galaxywars    false            �            1259    591543    messages    TABLE     `  CREATE TABLE public.messages (
    messageid integer DEFAULT nextval('public.messageid_seq'::regclass) NOT NULL,
    touserid integer NOT NULL,
    fromuserid integer NOT NULL,
    title character varying,
    msgcontent character varying,
    created timestamp with time zone DEFAULT timezone('Europe/Berlin'::text, now()) NOT NULL,
    new bit(1)
);
    DROP TABLE public.messages;
       public            1920-Galaxywars    false    182            �            1259    591558 	   passwords    TABLE     l   CREATE TABLE public.passwords (
    userid integer NOT NULL,
    password character varying(64) NOT NULL
);
    DROP TABLE public.passwords;
       public            1920-Galaxywars    false            �            1259    591563    planets    TABLE     �  CREATE TABLE public.planets (
    galaxy integer NOT NULL,
    solarsystem integer NOT NULL,
    planetnumber integer NOT NULL,
    userid integer NOT NULL,
    planetname character varying NOT NULL,
    ressources character varying NOT NULL,
    buildings character varying NOT NULL,
    fleet character varying,
    spaceportqueue character varying,
    lastupdate timestamp with time zone DEFAULT timezone('Europe/Berlin'::text, now()) NOT NULL
);
    DROP TABLE public.planets;
       public            1920-Galaxywars    false            �            1259    591553 	   techtrees    TABLE     i   CREATE TABLE public.techtrees (
    userid integer NOT NULL,
    tree character varying(333) NOT NULL
);
    DROP TABLE public.techtrees;
       public            1920-Galaxywars    false            �            1259    591075 
   userid_seq    SEQUENCE     �   CREATE SEQUENCE public.userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9222222036664771337
    CACHE 1;
 !   DROP SEQUENCE public.userid_seq;
       public          1920-Galaxywars    false            �            1259    591572    users    TABLE     "  CREATE TABLE public.users (
    userid integer DEFAULT nextval('public.userid_seq'::regclass) NOT NULL,
    displayname character varying NOT NULL,
    prename character varying,
    surname character varying,
    email character varying NOT NULL,
    created timestamp with time zone DEFAULT timezone('Europe/Berlin'::text, now()) NOT NULL,
    birthday timestamp with time zone,
    lastlogin timestamp with time zone DEFAULT timezone('Europe/Berlin'::text, now()) NOT NULL,
    lock character varying DEFAULT ''::character varying NOT NULL
);
    DROP TABLE public.users;
       public            1920-Galaxywars    false    183            m          0    591532    events 
   TABLE DATA           �   COPY public.events (eventid, typ, thisgalaxy, thissolarsystem, thisplanet, targetgalaxy, targetsolarsystem, targetplanet, building, fleet, ressource, starttime, endttime, userid, targetuserid, lock) FROM stdin;
    public          1920-Galaxywars    false    184   +       n          0    591543    messages 
   TABLE DATA           d   COPY public.messages (messageid, touserid, fromuserid, title, msgcontent, created, new) FROM stdin;
    public          1920-Galaxywars    false    185   ,       p          0    591558 	   passwords 
   TABLE DATA           5   COPY public.passwords (userid, password) FROM stdin;
    public          1920-Galaxywars    false    187   �,       q          0    591563    planets 
   TABLE DATA           �   COPY public.planets (galaxy, solarsystem, planetnumber, userid, planetname, ressources, buildings, fleet, spaceportqueue, lastupdate) FROM stdin;
    public          1920-Galaxywars    false    188   �:       o          0    591553 	   techtrees 
   TABLE DATA           1   COPY public.techtrees (userid, tree) FROM stdin;
    public          1920-Galaxywars    false    186   ZN       r          0    591572    users 
   TABLE DATA           q   COPY public.users (userid, displayname, prename, surname, email, created, birthday, lastlogin, lock) FROM stdin;
    public          1920-Galaxywars    false    189   �O       |           0    0    eventid_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.eventid_seq', 569, true);
          public          1920-Galaxywars    false    181            }           0    0    messageid_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.messageid_seq', 3, true);
          public          1920-Galaxywars    false    182            ~           0    0 
   userid_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.userid_seq', 205, true);
          public          1920-Galaxywars    false    183            �           2606    591585    users displayname_unique 
   CONSTRAINT     Z   ALTER TABLE ONLY public.users
    ADD CONSTRAINT displayname_unique UNIQUE (displayname);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT displayname_unique;
       public            1920-Galaxywars    false    189            �           2606    591587    users email_unique 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT email_unique UNIQUE (email);
 <   ALTER TABLE ONLY public.users DROP CONSTRAINT email_unique;
       public            1920-Galaxywars    false    189            �           2606    591542    events eventid_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.events
    ADD CONSTRAINT eventid_pkey PRIMARY KEY (eventid);
 =   ALTER TABLE ONLY public.events DROP CONSTRAINT eventid_pkey;
       public            1920-Galaxywars    false    184            �           2606    591552    messages messageid_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messageid_pkey PRIMARY KEY (messageid);
 A   ALTER TABLE ONLY public.messages DROP CONSTRAINT messageid_pkey;
       public            1920-Galaxywars    false    185            �           2606    591562    passwords passwoerter_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.passwords
    ADD CONSTRAINT passwoerter_pkey PRIMARY KEY (userid);
 D   ALTER TABLE ONLY public.passwords DROP CONSTRAINT passwoerter_pkey;
       public            1920-Galaxywars    false    187            �           2606    591571    planets planet_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.planets
    ADD CONSTRAINT planet_pkey PRIMARY KEY (galaxy, solarsystem, planetnumber);
 =   ALTER TABLE ONLY public.planets DROP CONSTRAINT planet_pkey;
       public            1920-Galaxywars    false    188    188    188            �           2606    591557    techtrees techtree_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.techtrees
    ADD CONSTRAINT techtree_pkey PRIMARY KEY (userid);
 A   ALTER TABLE ONLY public.techtrees DROP CONSTRAINT techtree_pkey;
       public            1920-Galaxywars    false    186            �           2606    591583    users userid_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT userid_pkey PRIMARY KEY (userid);
 ;   ALTER TABLE ONLY public.users DROP CONSTRAINT userid_pkey;
       public            1920-Galaxywars    false    189            �           2606    591588    users pwd_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT pwd_fkey FOREIGN KEY (userid) REFERENCES public.passwords(userid) ON UPDATE CASCADE ON DELETE CASCADE;
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT pwd_fkey;
       public          1920-Galaxywars    false    2030    189    187            m   �   x��ѻj�0�Y~
�%�\tt3^J[�K!t���/
�N�o_KIm��E���;�$֪����I�r���O�mH�*�����n쟻q:��G7�c���" ؁��bޤ��ۈ ���u�#��J�[t�AgjG�W'��F���E�N�9����ge�6$�HQl�,w�p�9D�\����a-�e��i����0}/�a�.����P�ld~X-p? Մl4^�P�����UU� �{n{      n   `   x�E�A
� F���)�G�Ϥ���q�+#"�����O�?��N�L��J%����+�������к�@�*%�L���?b���,�N߳Zf�j7�      p   [  x���ٱ-�E����H��8����q���'�n��C�$;˩���5�>uT��vͲ��$Y�u|�]o�Z����8}�~[n����B5�Mr޶U�7�jս��g)ː�cy��rr��N�7����#�K�>{�hW۹W�OϮ_M�)?�9?�]w߹�w=��3�k�WɅk��J�݋��o����6j�t]�g�{�EY�V���W�ߺ��#?��5�C�,��s�5?�P�5%�R2�����Z����u޹��O~���C���RS�a[v�{����{kɻ�6n���������?`L�����?Z��42�s�.e���ZJ�8Z$�O[�]j��������u��Ϡ ��iO}��]�1�E���O{�Җ��{���3m�M��v����Ҵ�%峒�v��mc��{�g>�6�w}&�}�g8�;�~��ބh�6��iҼۨ�X�ڭ�U���m��y��=\7�޵;�����k�����SUd��u�fN�sim�G��U(�;4������:f�9�܃���]�8���y����'�o�|ܽ%V�+��"7�����K5Sӓ�Q|���*�գջ�Dp;��s*�딑[ݴ��lW�����#���
p<�	�Oum#C� �Fw�\���
�s����1�#��ut@��0�� �n��k^� .����-yc���F>�a��a
<��sO[�-{�P����c-��y�����H���\�r�Ѯ!�w��3�N�8�洗v��[깿nY�.H�yU�:�|�l�)�ǚ������V�G����t%�����~U�ޛ]��`�}��B��8_��[�^���`
z��=)�'q�W�3���[n/��`�
��Ec䯂g��N���|����o�����`�ӽ��n���o��e�jK%�
e�⇇l�X���:
z�h���Y[��Em=�"�M]_�t�u�>lTha�ù�8���$��<���4�!��FAT(5�.��v1!�����U.�ޘN{��m���>�����xÖsa�Zo��/})�\�U�&_�m�"x���׫�����a�v��>����t;#V��	�ņL`A�"��Ps�-,�Ry��ڠ	��/Z�K�P����[
{��Յ�2?�^=��`�A5�z���YZ^�}k�ZNQ/��p�Kmؼ�؃6�VRᘕA�ǡ�L��#�Ġʯ�L�f)S���:L�������5MH��7FN^�\t�S���X�c�g`�d�b�������<5ùN��Ve��6}�O�4`���6みs�`]n���	%���H6�5��Q���!ܨ�l�|k	|����ytwC^���W	jz*��xy�	�r���� M=��`��Ɣ'5!�h�)�\�^p�P #�c���ެ�缾.�6Aq����5�T}�!! _W�Nc3s�c����=f��%����צ
���M�x��g~�3��Wj�	�7Cw׀ԖD��sj:s�Z�%lafc�v��3���=�>~�����5oN�~"�ͯ�t����)��FZ�G����d�$��tQ�~������d�߳�3opc����T��� t�0�������u�A�nn>(��O�+��/���OԱ(Zޓ���}�-
������g|2E�GG���m.9�������E�'@�g%�7;$~�-�1�)��Mu3��S�u�n0��P�;S$�h�dD�sq]�L�X�}���^��d3�){a��Ȩ�B��,�e�Fk(]��ͮ�����:�X�/g ���P̅�
�&��<�,����,�c6;ϝ��S���{���n�c<C/d�w�4q���`�c~���H���"K�s�n����x#����3��y2�n�Tb���T��Y�T��+��I�֎ގ�@��2(�$�$��l�Q�N�`���ݮ��*;D�Aa+jI��A)K�&�-�i`? :/2�2/�qd^�Ek2�����M��L$7s&�b�1�������|�Bt�4���a+�=3r��ɀ1"�4��R�-��U",Gj�/@�YB77F�Q�ʆQL���;��|�dpP,mzZsg�˙a���:�iN�g�}@> \\�чS���|�
J�[)آR1��I�rsQ��G؆��g�D�����e0��$�G��[ڎo�Ox�c�k�|s&� YȰbcG��!l�@}l\�CYp>�L.����{QXvxf�"�� D^�K��q�į�hF��0i���	���<��.�.)���A:���p�q���i	�3^�EPO�Rη<��^Q�Jt��lQ��t��B^���mx�8G1��_�L�/�&���;�C�.�������痏<�ǶO}��� ��0�<-##�J���?�YU�?�Z�!��,�`�EZ��8����@��e�C a�)j	7�i��������q��I=������x����V;Z0�"d��刋�

��^O�>XrE%9\K�ǡ�`���hO���"Dp|���y�&��q�f�S6��\
�?)�9��M?�c���;�[Z��c{:qU���K]@�8���|{�l��鰿�̢�����3.�t6�%�l�>Rt��I)��sh;MQC�{��Z���$|��h��;SG�\p��e�lG�7�͎0�i��`�;�d���<����I,�*��$���F�{ΜO����8I=�:Q�k8�����͛9(( O�	2C�~q�������\Cyh+E�����B������uU��E���(q���,Rl����!;��("���NƢ���t���M���;2�=2T*��J���� ��]���� 5�/�&L��3@&��3,�W��96����G�q
��AF�.����Ʉ�����ds&r%�>�Gk�;bT�`E�/�� &h0z~���|�u�}4B� {�%b[q�	P\,�ޤ�=QdvD�<����cc����X��x���<܃�:������RD��;n*:M����Չ
�h����F�# fG�,���������\�#2Κ��B�q���J~V����V�ԫ��_�z��]���>1��^ד2&Q�Wl��=��m�<1��9E�!��v�拽���O�a�/'bn�����1�J���~		�Kc!(R|&\dؗ���a�����"���	dIg�[ ���o���p\x"eb�P|��i�\b0M��[�B���X��	$BUv}�9g ��D���,�A�-w�|{���I--�vȅ�G8~�����@��vK�3�8���9����_0��b�6��ă*6n�e��Jbq޹���lq���mY����HR��� �����i��g,";�$m�gҸ��v��I򤑞1���x~�����zǻ�5h_�9�k���<��g��i:�f4Ʈ�3'I+9��$�u=8 b�|]��/���9З�<���~�s&7S���$g�nt�$�nU��z
���<�y���+���b��	h���e\
�Qy	N��=>9�"�SZ�K�B���<�1�^�+�����Y]�����F@�fdN!s^�̜�]G�p��|�~K<8.E�l5��i��u�wbved�K~��?ᓐ���;0���R/���������pP      q      x��]Ir"K��[E�����}C�3+��@���$��.������_��@l��Z���,SJ��=������ �'��/����_���������m�ۆ?jz�i����w��鍧�����u��=���.������<���_�{��~16�w���[l��^�ޤq2on�{��?��g�����矣��`~��|�'A��ğX�"8Ch�0H�T��/�`@	�� ����B$���!#���~�����NK�I���a�	J�5Ѐ��������l6uf �Pi�Qt $������~&c�!�D3�@E�`���`��ŧ4�$�&*�i{�S��m?��Ad��Q������ �;p�=;A�
f��\j.�����*\/��9��;���0
)�J�����9�����e�&@�	E�)�"Fb��|�< *�d�c! (�&�a�M<�]B�1#�!Ȥ�*� 3{�'�u�ܖ�[{�0d�Fw�K��G��g���CŅF�CL�q���A�m`� 6�Cn>�8��*�����v�:Tf(�J	�b�0�Mtd�w0�1`aV #���Ԁu�zo<�gn��l&	L��@px���m�	3̜�8�Tbx�M���<�{���"1�R(R���}ކl+�ͻ��	�q$B`ǈa��0�]���: �g�Bp�#m��BDD��xp]'D�c�E��&
��&�|���G���6��
rE5�`�!LΓ���>�x�jm���6��@��|����i�Z�w��p�X�����w�L�a�ʰ�y�6|��i&�=�'38q9��A�ɄQ���Ri������:��m�ͨ21&I2�Z"���Qs� ��	��%%2��߬e�颪��+@l&Aje��
�QUe�rf��6�#)���`�
�������g�DJL�$�����z\ھ� �TR����� �	���c[��4�`�q���"�V0��K��z}WriBe������0̂{i��
�5�3�	
*̤��'`���w�א3�D�H�(�|�.����;����TJA�O�O�������|� �{�?.�i�Cµ��T�6!q�s�G��'!�O�Df��^7�_6�A �13�B�x��f��U�Q��
����NLB�4�F̬�T�r+���GP�1JQ4�m?Vi�X�*�֢��\�M`��(E0P۔�X׊�����&��K
`�2*�vL`P�c��nn��>w�� ���!!�L��������� `bHI��`�ee��m���V� ?5aQ<N�[����&�7�sɉ�,lH�?��;�s���ɨ���.wf&0p�5�4.��ux.���}�r���O���$�2�˶Q�K�R����m�v^ն" ���a�/۞��]�׫Ӆ�'��&)�t�YV��� ����G�Q�Ā	�,J�Pn�\��e����^�u��x�iBG9L�oV���b�us����� 4%�ƝZ�y �r�����p�vi�AC�	����a$6ոh�K���>{^ݦ�
;ډ����PX��λ�Vx�YE��^�y�]��� ��Q�t�c��m��F�E�g�U$<�>��Y����.oi�C-u��o"&m����F�%�nT[�i A@�XRZ0��N�jP��F�|\���|�p>�h��PI"b��m�E�*t�=����}��:�CXm�Lr��
6�*d;���<��Η�HXvB��OQ�J�$`�p>�DC4n��-�|����y�i��An"h��^$��u��9�`�ń۔��`z���e��$���5�Zc @i~
�����(O��H���.)�*믂�b0���ѳ0(�Cb1�@�$(PM�"А��#a[`���a+�)B86�\����8��ڵ�v��)�,��4i���	
��x5��W�����g�+	%�I���"`~j�?ϵ]�RM�x��Ů�ш������G�7J��D�#��luI1��U�ۻ�Sm	 r$)�ъڎ�~d�{����;3��.,c�)�9�*��b�� O �
�d�O�`'~�[�f<�G+�Ϫ��)X�p�3��Qk��F���P*Ԗ�����D2�U2���Rݯ�曗�%Wr?���F?�(�Ŵ�����>ڏ��
m;18�XG8ߙ ���[����Υ	G���If��A~���ܯ�iۛe<F%u�e ��8;�y��/7f�M:p��~*���B��-���Fη,[d�MVY[i��Gq�����i�|��AB�%K����� ��s�� ����Ӹn��������@']��Z������� H���@mׅ� �u����^�ݺ�s}�ЄJV�K`�I����y;w�����r������3F��A�}xw~��b !�}�1H8��ϳw���k�/��[F�F.��!W�	
�Y�e�1k��9G�XH����K8�@�����u>�b�P"��&v2+rP�Eo;Ye�i@� �0RD'�Җ_�������w����!�&l�{�B�i$�g%x|�{�^;��S�%F=҄A��
��;׭����IF���k���V~*����yܸ
�Q/�~��i �N���U����m��o������]8E?�P阅[�^#&`�w]^˓�o"Jy\��2<�4����^_�~��OCLM%d�腟�=�,a���Z1,�y�^�I� 2�$�r�9����e�l��ns���B)���c*,tF�~�r^;��~v��\��1�Æ����6.;� �������c6���q��mv+���P~�������4`��$H%k�>Lm^^���[ l6��X2��	����Fz������Җݰ����Ga�p���"�m+��?l��
�m�'z��RDe,=��?�z{Tzsy]O��v-�SM�	��<�;_{���"$�B,ɞ�P�T��w�=��i�RSӦ)a#g"A��������#^�
�����@/W,_Y�m��@@$�iQ����_{?:ߠg1��*��1�0�L8.��l����]	
rl~�H�0�H1��ji�^CB��4I�	#=�S�-xt����e�<[�`�K�� Ix(�~U�nzʯ(������ś_iw���s�BZ�D�@��N�A��R��R� ��`�&j˧�� ���G���>��2r�y��9��H�'kN�J��n�/*�]̡���6d�ʒIN��v~;:^d��Dв�s�c>*�A�C����'�f]IhbÖ,��s���(C�#3)�Um�A2��=W�:/=��E�(��&"�	�?8�%�RW�!Bv�/���4�����&�5e��8>������b�\�sK�F$�*�={��AeUs���ȯ��c�~�"m���9ߧFi��::N	�BB����XȊ��/����� 1��N#�%T��6�CϿ,�����>�'�Nn�8V��T���0x�^~��9N�"A�B&e5c8�|l��?���=Q�Q&��v�s��ls�8�l��R%����EN��k��7�{poM]'|"A�F�ƕvj�1���u|j�)=��#o�1a�q�8������ꎓ`<1`�J���*p�*T
/�_/��G����_�f����4Z/��t0"���e��0b\%�TV���5>4/�I�i��� 	����ا��bJP)f���ܩ|�M��`�R ��D;����4wk���V>(hȄLZx[s���W�\כ�_��ݦ"B�T��t�zS P���]~���и�B�$�f.������\��R"HؠD~�h__�[?�5��/�ݕ�@!5�>�R�$��ܗW�������,x��Dg��ޤ`v*�8�*�s/�r*T���(� ������Zl�V���vt� ��`q)��Z���壗?����vM�	���fH�Ez�E{iF�y��r�rX8>��`
LY�0M���y�Hz
�4�vܼ΀
����mj�H~��4<?~���o X  k�t �0EI�!T�7�����G.�#i��rR�NKڛl�s�!#A@YR�x�'l����j�r[�!�B$�vcEܪ`�������u���o�@H��؏�h�A����i�maN�,uա �����2��Y�]�	�I1�^ 	��f��u�!`�k�*���l��`���Zڝ�n��FXvF�M�	����ҭW�����)�=�� �8R?��k�8�օ���P��n�aD((�qφ�V%(��Kq��R�x���Pz���%�+�OEq[�+~��൰���[*��m^�	e��/L�P�����}k�_�Q!��P8������`4X�z��z>{��[�-<��SW�CB%@���9L��6�� FT�=;$�U���:xxn�BDp(��"֐�]T
T����ͽ��"�4�g@��^J��mS�Ww����s?��m� qOz�0w��n;�rq?�9��3Ar��,�
M��`8Y�'�c*��B��f��c,���t�**@CFP��O���/[|k.�~��x����̵fqOkX}���a��6�f���J�l)11�� ����q�t�5'����(�0� �^��a��]��qq4t���C�a&(�9cPh$������������Hҭg5��Vy�~�=�_�A����WA����߂����?�ܯ5q�=,!"��&`�8��A���@J�,��Nr�Լ���j����ɛt�7�qK�K�W���ٗ��t5m��Ҳ��yr{7r�����\��lj�"
��R.̺M��@ Q��H����缹�||�k筒��Jw�P*HF�Q!x�����͗��3�����?��c�      o   �  x�%ӻu�0ј(������P	f%j/��������̯����ܯ�r���p�C��E�F�hM�i4�oh�7�;���/p�9�w���t�A3h��6h�3�ݠt�n�M�I7�_�I7�&ݤ[t�n�-�E��ct�n�-�M��6ݦ�t�n�m�M��ݡ;t������ݡ�t���]�Kw�.ݥ������V7[]mu���V�[����?��i<��'��c�%¤=='��8iNH��PQ*RE���\�N��"[t�pQ.�e�g�	�"_�Q0FÈ3�c儐Q2RFˈ5#g�Q4�FӈU#kt��Q6�f�g�	u#o��Q8G��#s�{�P:RG��#w��Q<��&�=�EѼi�4o�7�J)urtB      r      x�}{Iv�J��ش�V�I@#������S�}�$��֠�vÍ�"#$T/3_�|�k~�n�5s�������T�d}��>�ě,�(��ڿ@yy��í�^���|遜*�
j�I�i�.Q*�$M�&ۿi�E?��s�!H��h���P<�����4��f�)��T���=�عo:�|�pz��3�׮a����-=M
�3��$�4J���F��&y�$���S�/��̲����_�O�J9N���%:_>�-�������9?���yb
���y�E�h�d��n��&[���/��L���V3��'��DKǢhg��Z�]�qr�,�#�q�{�<���@)iA$dy*zDHf������W>�m[���L���|=�-X�/��/�J� gsW�D���^����4Z��(*m
9�H<;�3�	IoX�MtZ���uydE��ڿH��F�a�Y}=�m���}��n+)[��� |!����J��J�`;��ڏV�7EV�˶h�敿a���͵��
�p��+�l���L�mG�R;!l���7Em��c�.����O�E2���qz�9�}<+�ew���p1�o$0�DM���rt�Q�3L����;\�4�:[�&�5�������^/N��ju�8�)��t{p>���:U^6o�8�_��
A(HL1�S�`#�c��C�x���d~Q�3�yf<��D��ʵ����3������yx�AI��d�������A�;m�'�W��~a�j�������ڤ-�uv�3,�S�
�A>�1��E�Դ�üO^�A�h1$O^ݏ���(U�՘��AȢ����<z��MRP��@};��m����r�K�PHB+�sEe�'����YpulP��}na��,���E���܄����[��[a]��Z��	�n���A��s,�q_���Ъ�
���!���g��㛧����>����z����d��As�<M]�t�HS��0C���L�:�S�tņ���p��l8خ�5\>��mcn��I���p�L��CB+�K4�鴩��3)��	
}Âzf�s�? \��h���j����8�e�����ũ`z��kB�����5�d�Ͷ��o��6 �6&�Ɗvz�(
��{s���Ǵ�:㞺�tמ���z����4�&ȉa�L�ՉԾ� �X��v�v8|�Uܒ�3M?ST3�� dE��d��C��Bc���=�������mg��D�R_@���D�6L�@���d�BR��RH���B*�����ZY���,#.�ѕ�Q�[ϧy�v^�&Y����y��EYB�Jө���}��!�e�/�{f���)�0��ˑ��|��q��jp-�� �������&�!!Z�:D�a8�s��N�����y&8�� $�U?��fm��a�y�����(뿁�E�ᨆ���k�n)r�qKw(����dI�Jm���?l�wi�(=�����\�|�/Y��C�85Ǵ��b͍�3|��#���se�'(��n��f�
kd�?��w��@L��<)�ѡ�?\>4q�������4,rI�$��@q�"���\eP���H��²�L7��;�� �g���k.��4C�������R%Z�B�yD�P~�d��i��a�Aa믘����B<�NkI�G���@���n7���B�I�����i+8��ث�2���ݨ��*w�'��@ˎ���.;���#��BA�5��J>�U7�î�ֺ�fw���~��4D��PY���e�36o�k�����l+��L�9�-P0J����<+S���S�Q��ҷN�1,M��۸�h�!#��
g%Y�8�.��bF��~"��Hj��(0�6O_�E��R�_�VU���Y(V;�e,��������6��`rt ��"��Bp}S����i��Q�^i�lI�:.٦��!�Dg��ч���HʗZ	��ٶ�+�`$��<����6ߺɞN��e�/�z��K��m��s��~y�uK�@̺�n9|[[�XZ�������==�B2	5u����4y=���fJ�����t���v�Y80\>.��_)�l6C�;q)M�H�Ē"��z@�c8���,�>��� �����=�p �F���arR�#�����H!�4�s�L-1l���U	��z0t�_����a3�� $��a�Jo�i����I� �,��������0@45�8=tO;k0�&�����d�)��s�{�Ő����]�Cȗ����S�L�8޽X�q�򙂨G�&���dӴ|��{��r��P�V<���W�`=m<� $C��\B�w� ���^^$P�",S�[31a4�tH���~U=��Y�JLs�x��iTă���0����wKpf�RFBS�P6�x�a�Q�}Q���e�4�F�e��=nݜ�u�*(t����!�p�-��Ư0��y8;v�[Pt��l+lW�����tZ7eS�����-��75L� \������K`�^��{�R��}K���R���]�PNJ��%!4ݻ��T�)��x��צ�Sk�V=nb��}!���|y���Κo���s6��a�����*�8���Ğ&^hz���t/A�,s��5�y��g�ģ��Ot���N����e{i����tuz��!M7�rzZh�d�E.�i�FJB8A�]�hw�d�Z��N]a�Jc{M7g�@H���-�(1vD����H��M"16������1UƪF'4��<�ؾ���~ �D"<��"�"��X|�M5AUUB���sU�G��z�F?��#��b�z�~9R�	���5[���9�{�i���㧿@\��?����bQ�i��݄�3ݮ~K��΃����!ڏr��4����6Ȫ�昲 ��J��f���:As��l��rj��O߁O�����SXD�`t�|�B�_>�8�'��B��#<��NW}8�;�Sd�n_W�a�i������ �m��m�n��]7"�w��m�2Q�i��o?*ۯ�[�B,���A���N����+��Ъ
ǲ4�P�����t>�@dЂ��G�d#OCX\ޕ������	�T�F�`YAb��NXV|o�6A������4x5�Ǣ�� d҆��]�����x=�70Q=��'����'ْ"	�������}�~3��H���
.���lS�x!���,:�x�_>����|�dڛ�X���7�3+�0�;��	�ח���FqW��+IW��y�A�>��
��y�9�znM�/�n;�|qy�.'�o�6C1bO�$!43�M���������ڄj�ө��]~(f� d�������/�"j���!���~�����������B��R��{5��B�{��L-<�j�T)�v�;�j���'�v��kNy����2R#�"�q��JM1�aZ<�\����(�׽#f�����Spv7�	Y�B5��)F������MQ�cA�uω1npV�"��nE���s�`�i��� �@�F�`S�I[W.��|2\:�>�Cj���l9�����Z��~A�e�`�Ƙ�;��z,J3�\�G�����Sΐ:{P���
�SSA�s��8*��:�����P�/��t=��G����c1���j�p������DK�C�U}USc�$B���ڹ�x�z����������B-�g���W�_��������f9���c�D�Q|/:P `>�O�խ2*7�C�9��w��Jy���ڰ��M���`����E=�����u��u��2%Ȉ��'L��Ic;F���-L����W����� $с٨;Y�{����꽼��VsXi�\�ew�/�~b
�I�6�����f�_RU�JWw��WK $��7V{�����Z�����ʨK��q).K�L9�|T�F�`��R$��_#�-S��ۏ/@Ȅ��y�����p�N8g^yz��`����E4,�vc��4J$"���<6�l��W�ݾnW�Iy��4���6	���뤛��s8���f��$+���+�T(t�PmQ$�õ�?Y��JO�e���	PP�B�0s�g�]����[� �
  �^(98�i(xr駄��jjr�P|�]�`�6�U5B�4���a���.��>ʠp��N��z�,\�3=�t�׋�L�fg�sU m�w�!U|V��>�4��q/�Ay�Tռ ���
� ����$��gPj�l�� �d�Z4Xe�v����\�����%�1���0��N�^?���P��C�"�h�>8z���QN�qӞ��$1Y�F�L=��	-��.��DZ�]>������^>ϐ��L����a��N	b�h�4?�d��庆�E�Y�"�����|;U����X���\�	��0?o !�6x�<_��*M�o���.a~r@�
�ɒH���PXTw�<��[x�֨J�c�id�Yt`�ɢ��d���R >���˧F���V����̩�o��*H�E�b�e��6��z���\M��7����\%(-']�����X�|�v�9�.��9N�]���	��KcTp�5o�N�i����=^�5��V>9�ࠞ�?������#��e��������b8�B�N�E�`�6�G�Q��5U��4L�@OT�Wߌ�8��]/^L�$�A��j膥�y�Dd��X4֢��N-��Ĳ�(͹�2�`����pry_��X�Q8�U����-/��Y�Hd�w!R"-���c[�u�iU[O�հ���
5��
;�;+_�ڊ�[%���6��T_*F�'#EN*X�X�6a8v��6�t(�6�co7��U����BH��|=+��Z�M4��br�$
xv��`i�zCV�^��֟��"�~E6̑@ȁ��z��j��N)ˉ��JV˼���ř�Kv�ņ�چ�FZh�X �F��"�os=�҇���i��%*��^9���m@�2(v������z����A���v�]}��	V��g�Y������MJ�=jŃ|�b0��,��"���[=����<�G�Uc �<TO{�����v��nv�nF�^izqw�)N\>-ei�N��)q�@+\!�Ԓ��Y��<��7AD�o���Tu���� �ā1���	ҮR`k�:��z(ܧ�9h�(cmSTMpCǷcl0��,��P{�}ݍ���V�������j6?��;��	�L�a����k�C��!��<.<;*��,����W�g(��M���P�L���:Z?0�z�8����k<�W�/�~(+�h���q�Ű/C��Z,�� �q%2=T`�zJ���{w��T���Z��ށ��l�c�;�Oc�#�b��@��x(n�����-^�?U�&f��6����0���d�@�h�h�r�@*9+�D�]Svt"32[�QX1��$�����$�@ȁ�ס+n�|<�)���l�CH���L�~S�;~�l=+f:{K�|�5�ui�{�;`�e����hk����2Ÿu�����츃�����	��s��AE)�-��Z�u���6{k���k����͢���Z ����UZ��T�Yo�_%�]����Q��"�A�Z)h�$g!71O���;�*��y��ߗV�˟��!�6�ӱ+��8��`��a~V��rٕ�P-�H#W	�A�e��{K��j_ȯ����qyt`��*��S|���z��W`�U[ҏ��qa�z$�M���8�G����?S_�]T�d5�	��,xؕ�r0���,M|���j��o0��'D���P(nBx9�R���G`GSE/��|s�ށ�0�����die5`~�e���fQ]�۰���	dV�Yy�ȡ{��$���C#ު�9���u+��:���\�m3�� �D�Y1e�'���:��ڠ���h\>wF��@��2OM��JB�|͵]�����]V��|=H&Gj $�:q˂�|�7?]v����cP��.�P%SqBߕ��!���6�,}#Q�X��>�llg�@H��;o,s�vLO{袀V�'�pm�֗�q"bS�����nD����UM��U��K:�x�ꄹg�)�܁�kw�2hb?�����.bÁ�p�f����ܑ��Q�G���C��n������m��Յ ���R�R�!�
C��^b�����s�=��(����u�����a(��;׋܍�Ϻ`NW���6�p�6D�������XIg2{���X���&��঑k� �Ѫd�csӪF��Ͷ:rm�h�z|�6�$k !�j�˧҅�:F�;T+��� �6���N��%Q�k��ل�?_�2Kth��#�:��w�,�8�o !^�����p4G��<̋u�\��t\UJ�Q�V�]���^+S���}|�G�+�\��T����#z;b��`]J=��&�J����l�`�����+vەd�ܲ�\b|��2�AO����?�&���b=���ax%Ȩ�>d�m�j*��%�&�^�����J�~)���'Ӝ_�!
}�y	�%�C����"��Z������y��y4+�TOSER��ڟV�ڹ���V~�>=���a��4���j�����Ś�A:E۹�O 9��
�����$�,��D�Q�q�1��������i�xD�Aȅ���Ko�t'�*�`!'��� �b	��ǁc
~le��<��Z�+g<���"��z�TU�� �a7������f���Y��3P�Jry�|�M��P7RU,_��JK6��0ՔЋ���6�wH�v_�W+7��H��M���*IM^>�Uoo٠������i�������O�^C��.��'��?���W- ���+@O�C<==�/�ct�     