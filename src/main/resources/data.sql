INSERT INTO vehicle (registration_number, make,model,year,description) VALUES
  ('6Vb-9za', 'Ford', 'Tundra','2010','New Truc'),
  ('7Vb-9za', 'Toyato', 'Tuson','2011','New Truc'),
  ('8Vb-9za', 'Honda', 'CVR','2013','New Truc');
  
  
  INSERT INTO vehicle_Insurance (registration_number, policy_number,start_Date,end_Date) VALUES
  ('6Vb-9za', '987457488263', sysdate,sysdate+10),
  ('6Vb-9za', '987457488293',sysdate-2,sysdate),
   ('6Vb-9za', '987457488113', sysdate-5,sysdate-2),
  ('7Vb-9za', '987457488223',sysdate,sysdate+5), 
  ('7Vb-9za', '987457488443',sysdate-3,sysdate),
  ('8Vb-9za', '987457488993',sysdate,sysdate+3);
  
  commit;