insert into role(role_name)
values
('admin'),
('moderator'),
('user');

insert into users(user_name, role_id)
values
('Bob', 1),
('Martin', 3),
('chip666', 3),
('_dale_', 2),
('gizmo', 3);

insert into rules(rule_name)
values
('delete'),
('correct'),
('confirm');

insert into rules_role(role_id, rules_id)
values
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 2);

insert into category(category_name)
values
('low priority'),
('high priority'),
('normal priority');

insert into state(status)
values
('currently under review'),
('confirmed'),
('denied'),
('send bak to correct');

insert into item(item_title, user_id, category_id, state_id)
values
('create account', 2, 2, 1),
('change user name', 3, 1, 2),
('get all system logs', 4, 3, 3);

insert into comments(comment_text, item_id)
values
('previous name was inapropriate', 2),
('suspicious request', 3);

insert into attachs(content, item_id)
values
('passport number: 7845 6459 235', 1);








