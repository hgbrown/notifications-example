INSERT INTO public.notification (id, created_on, first_name, last_name, recipient_id) VALUES (-1000, '2017-06-13 07:14:52.845000', 'Cindy', 'Crawford', '1111111111');
INSERT INTO public.notification (id, created_on, first_name, last_name, recipient_id) VALUES (-2000, '2017-06-13 07:14:52.883000', 'Danny', 'DeVito', '2222222222');

INSERT INTO public.email_notification (email_address, id) VALUES ('cindyc@supermodel.com', -1000);

INSERT INTO public.sms_notification (phone_number, id) VALUES ('1800124596', -2000);