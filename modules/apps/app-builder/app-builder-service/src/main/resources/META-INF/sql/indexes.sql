create index IX_319C0B20 on AppBuilderApp (groupId, companyId, ddmStructureId);
create index IX_EC1E021 on AppBuilderApp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_65D5FAE3 on AppBuilderApp (uuid_[$COLUMN_LENGTH:75$], groupId);