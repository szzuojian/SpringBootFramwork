INSERT sys_role(id,name,description) SELECT 1, 'admin','管理员角色' FROM dual WHERE not EXISTS (select 1 from sys_role where id = 1);
INSERT sys_role(id,name,description) SELECT 2, 'default','默认用户角色' FROM dual WHERE not EXISTS (select 1 from sys_role where id = 2);