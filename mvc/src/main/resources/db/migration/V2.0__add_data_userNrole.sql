insert into roles (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete, allowed_upload) values
                                                                                                             ('Admin', '/', TRUE, TRUE, TRUE, TRUE, TRUE),
                                                                                                             ('Support', '/users,/user,/events,/evs,/answers,/ans,/questions,/ques,/universities,/univ,/roles,/role', TRUE, TRUE, TRUE, TRUE, TRUE),
                                                                                                             ('User', '/users,/user,/events,/evs,/answers,/ans,/questions,/ques,/universities,/univ', TRUE, TRUE, TRUE, TRUE, TRUE),
                                                                                                             ('Moderator', '/', TRUE, FALSE, FALSE, FALSE, FALSE)
;
commit;

insert into users (username, password, first_name, last_name, email, location) values
                                                                     ('dwang', '25f9e794323b453885f5181f1b624d0b', 'David', 'Wang', 'dwang@training.ascendingdc.com', ''),
                                                                     ('rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com' , ''),
                                                                     ('xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com', ''),
                                                                     ('wzluo', '25f9e794323b453885f5181f1b624d0b', 'Wenzhe', 'Luo', 'wzluo777@outlook.com', 'Tysons')
;
commit;

insert into users_roles values
                            (1, 1),
                            (2, 2),
                            (3, 3),
                            (4, 4),
                            (4, 3)
;
commit;