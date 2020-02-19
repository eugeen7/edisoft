DELETE FROM IDGEN;
DELETE FROM CRBODY CASCADE;
DELETE FROM MOTOR CASCADE;
DELETE FROM TRNSMSN CASCADE;
DELETE FROM AUTOMBL CASCADE;

Insert into IDGEN (IDNAME,IDVAL) values ('atfctr_pk',1);
Insert into IDGEN (IDNAME,IDVAL) values ('atmbl_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('bdclrs_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('bdtype_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('crbody_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('motor_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('mtrtype_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('trnsmsn_pk',100);
Insert into IDGEN (IDNAME,IDVAL) values ('trnstype_pk',100);

Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (1,1,'�����','����� �������� ��������������� ��� ������, ����� ���� ����- ��� �������������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (2,1,'���������','��������� - ��� ��������� ������������� �����-������������� ������ ��������� ����������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (3,1,'�������','�������: ������������ �����-������������ �����, � ����� ��� ����� �������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (4,1,'����','����: ����������� ����������� �����, � ����� ����� �������, ���� � ������ �������� ������������ �����������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (5,1,'�������','�������: �������� ����� ��������� ���������� ������� ������ �� ������ ������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (6,1,'������������','������������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (7,1,'�������','�������: ������������� ������� ����� ����������� � ��������������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (8,1,'�������','�������: ������� � �������, ��� � ������, ������ ������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (9,1,'���������','���������: �������� ������������� �����, ����- ��� �������������');
Insert into CRBDTYPE (CRBDTYPE_ID,VERSNUM,BDTYPE,TYPEDSC) values (10,1,'�������','�������: ����������� ����� �� ������������ ������ ������');

Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (1,1,'�����','����� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (2,1,'����������','���������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (3,1,'�������','������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (4,1,'�������','������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (5,1,'����������','���������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (6,1,'�������','������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (7,1,'�����','����� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (8,1,'�����','����� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (9,1,'�����������','����������� ����');
Insert into CRBDCLRS (CRBDCLRS_ID,VERSNUM,COLOR,CLRDSC) values (10,1,'������','������ ����');

Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (1,1,'������������� ���������','������������ �������� ��������� �������-���������� ����� � ����������� ���������, �����������');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (2,1,'����������� ���������','������������ �������� ��������� ��������� ����� � ���������� ��� ��������� ��������� ���� ������ ����������� �������� ������ �������');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (3,1,'�������-���������','������ ��������� � ����������� ����� (�������), ����������� � ������ ������ 8-�������� �����');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (4,1,'��������� ���������','��������� ��������� ����������� ��������, ���������� �� �������� ����������������� ����������� ������� �� ����������� ����������� ��� ������ �������');
Insert into MTRTYPE (MTRTYPE_ID,VERSNUM,TYPEMTR,TPMTDSC) values (5,1,'������������� ���������','��������� ����������� ��������, ����������������� �� ������ ���������� ��������� (��� ������������ �� ���������� ���������), �������� � ������� �������� ��������� ��� (�����) ��� ��������� �������������� ����');

Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (1,1,'������������','�������� ���������������� ��� �������� �������, ������������ �� ���� ���������� � ������������ �������� ����������� ������� � ������ ���������');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (2,1,'�����������������','������������ �������� ��������������� � �������� �������� (�����) � ������������������� ����� ����������� ������ ���������. �������� ��������� ������� ����� ������������� ����� �������.');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (3,1,'�������������','����������� � ������������ � ������� � ���������� �������� �����������');
Insert into TRNSTYPE (TRNSTYPE_ID,VERSNUM,TYPETRNS,TRNDSC) values (4,1,'���������','�������� ���������� ����������������� ���� �������� ������������ ����� ����� ��������, � � ������ ����� ��� ���� �� ����������� �������� � � ������� ����������� �������� ��������� ���������');

Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (1,1,1,1,4,'CRBD-000-000-001','����� �����',1,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (2,2,2,1,5,'CRBD-000-000-002','���������� ���������',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (3,3,3,1,4,'CRBD-000-000-003','������� �������',3,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (4,4,4,1,2,'CRBD-000-000-004','������� ����',4,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (5,5,5,1,6,'CRBD-000-000-005','���������� �������',5,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (6,6,6,1,4,'CRBD-000-000-006','������� ������������',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (7,7,7,1,4,'CRBD-000-000-007','����� �������',7,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (8,8,8,1,5,'CRBD-000-000-008','����� �������',null,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (9,9,9,1,2,'CRBD-000-000-009','����������� ���������',9,TRUE);
Insert into CRBODY (CRBODY_ID,CRBDTYPE_FK,CRBDCLRS_FK,VERSNUM,DORSCNT,VINCODE,CRBDDSC,CRBD_USE,STACTIVE) values (10,10,10,1,2,'CRBD-000-000-010','������ �������',10,TRUE);

Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (1,1,1,1500,70,'MTR-000-000-001','������������� 4 ����������� ��������� 1500 ��3',1,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (2,1,1,2000,100,'MTR-000-000-002','������������� 4 ����������� ��������� 2000 ��3',2,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (3,2,1,1600,120,'MTR-000-000-003','����������� 4 ����������� ��������� ���������1600 ��3',null,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (4,2,1,2500,200,'MTR-000-000-004','����������� 6 ����������� ��������� ��������� 2500 ��3',4,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (5,3,1,1500,150,'MTR-000-000-005','�������-��������� 1500 ��3',5,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (6,4,1,3000,250,'MTR-000-000-006','��������� ��������� V6 3000',6,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (7,4,1,2000,170,'MTR-000-000-007','��������� ��������� ������ 6 ��������� 2000',7,TRUE);
Insert into MOTOR (MOTOR_ID,MTRTYPE_FK,VERSNUM,VOLUME,MTRPOWER,MOTORSN,MTDSC,MTR_USE,STACTIVE) values (8,5,1,3000,100,'MTR-000-000-008','������������� 4 ����������� ���������',null,TRUE);

Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (1,1,1,'TRNS-OO0-000-001','4 ����������� ���������',1,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (2,1,1,'TRNS-OO0-000-002','5 ����������� ���������',2,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (3,2,1,'TRNS-OO0-000-003','5 ����������� ��������������',3,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (4,2,1,'TRNS-OO0-000-004','6 ����������� ��������������',4,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (5,3,1,'TRNS-OO0-000-005','�������������',null,TRUE);
Insert into TRNSMSN (TRNSMSN_ID,TRNSTYPE_FK,VERSNUM,TRNSMSNSN,TRNSDSC,TRNS_USE,STACTIVE) values (6,3,1,'TRNS-OO0-000-006','���������',null,TRUE);

Insert into AUTOFCTR (AUTOFCTR_ID,VERSNUM,FCTR_NM,ADRS,AUTODSC) values (1,1,'��������','������, ������, ��. ������ 10','������������� �����');

Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (1,1,1,1,1,1,'���� 2101','���� 2101',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (2,1,null,2,2,1,'���� 2102','���� 2102',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (3,1,3,null,3,1,'���� 2103','���� 2103',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (4,1,4,4,4,1,'���� 2104','���� 2104',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (5,1,5,5,null,1,'���� 2105','���� 2105',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (6,1,null,6,null,1,'���� 2106','���� 2106',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (7,1,7,7,null,1,'���� 2107','���� 2107',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (8,1,null,null,null,1,'���� 2108','���� 2108',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (9,1,9,null,null,1,'���� 2109','���� 2109',TRUE);
Insert into AUTOMBL (AUTOMBL_ID,AUTOFCTR_FK,CRBODY_FK,MOTOR_FK,TRNSMSN_FK,VERSNUM,ATMODEL,ATDSC,STACTIVE) values (10,1,10,null,null,1,'���� 2110','���� 2110',TRUE);