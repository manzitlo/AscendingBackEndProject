insert into roles (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete, allowed_upload, allowed_delete_users, allowed_manage_posts, allowed_manage_data, allowed_manage_comments, allowed_manage_events, allowed_manage_tags, allowed_manage_notifications, allowed_moderate_content, allowed_analytics ) values
                                                                                                             ('Admin', '/', TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE , TRUE, TRUE, TRUE, TRUE, TRUE),
                                                                                                             ('Agent', '/custs,/customers,/cars,/cas,/insus,/insurances', TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, TRUE , TRUE, TRUE, TRUE, TRUE, FALSE, TRUE),
                                                                                                             ('User', '/custs,/customers,/cars,/cas', TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, TRUE , TRUE, TRUE, TRUE, TRUE, FALSE, FALSE),
                                                                                                             ('Moderator', '/', TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE )
;
commit;

insert into users (name, password, first_name, last_name, email) values
                                                                     ('dwang', '25f9e794323b453885f5181f1b624d0b', 'David', 'Wang', 'dwang@training.ascendingdc.com'),
                                                                     ('rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com'),
                                                                     ('xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com'),
                                                                     ('wzluo', '25f9e794323b453885f5181f1b624d0b', 'Wenzhe', 'Luo', 'wzluo777@outlook.com')
;
commit;

insert into users_roles values
                            (1, 1),
                            (2, 2),
                            (3, 3),
                            (4, 4),
                            (1, 2),
                            (1, 3),
                            (4, 3)
;
commit;