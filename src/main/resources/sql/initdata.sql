INSERT sys_role(id,name,description) SELECT 1, 'admin','管理员角色' FROM dual WHERE not EXISTS (select 1 from sys_role where id = 1);
INSERT sys_role(id,name,description) SELECT 2, 'default','默认用户角色' FROM dual WHERE not EXISTS (select 1 from sys_role where id = 2);
INSERT INTO sys_user (id, addr, createtime, last_password_reset_date, password, phone, username) VALUES ('1', NULL, NULL, NULL, '123456', NULL, 'admin');
INSERT INTO sys_user_roles (sys_user_id, roles_id) VALUES ('1', '1');

INSERT INTO sys_menu (id, descritpion, name, pid, url) VALUES ('1', '增加普通用户', '增加普通用户', NULL, '/user/addUser');
INSERT INTO sys_menu (id, descritpion, name, pid, url) VALUES ('2', '增加管理员用户', '增加管理员用户', NULL, '/user/addAdminUser');

INSERT INTO sys_role_menus (sys_role_id, menus_id) VALUES ('1', '1');
INSERT INTO sys_role_menus (sys_role_id, menus_id) VALUES ('1', '2');
