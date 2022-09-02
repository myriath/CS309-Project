
DROP TABLE IF EXISTS `nutrition`;
#/*!40101 SET @saved_cs_client     = @@character_set_client */;
#/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nutrition` (
  `nid` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `brand_id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `upc` varchar(255) NOT NULL,
  `item_type` decimal(10,0) NOT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `nf_ingredient_statement` varchar(255) DEFAULT NULL,
  `nf_calories` decimal(65,0) DEFAULT NULL,
  `nf_total_fat` decimal(65,0) DEFAULT NULL,
  `nf_saturated_fat` decimal(65,0) DEFAULT NULL,
  `nf_trans_fatty_acid` decimal(65,0) DEFAULT NULL,
  `nf_polyunsaturated_fat` decimal(65,0) DEFAULT NULL,
  `nf_monounsaturated_fat` decimal(65,0) DEFAULT NULL,
  `nf_cholesterol` decimal(65,0) DEFAULT NULL,
  `nf_sodium` decimal(65,0) DEFAULT NULL,
  `nf_total_carbohydrate` decimal(65,0) DEFAULT NULL,
  `nf_dietary_fiber` decimal(65,0) DEFAULT NULL,
  `nf_sugars` decimal(65,0) DEFAULT NULL,
  `nf_protein` decimal(65,0) DEFAULT NULL,
  `nf_vitamin_a_dv` decimal(65,0) DEFAULT NULL,
  `nf_vitamin_c_dv` decimal(65,0) DEFAULT NULL,
  `nf_calcium_dv` decimal(65,0) DEFAULT NULL,
  `nf_iron_dv` decimal(65,0) DEFAULT NULL,
  `nf_potassium` decimal(65,0) DEFAULT NULL,
  `nf_servings_per_container` decimal(65,0) DEFAULT NULL,
  `nf_servings_size_qty` decimal(65,0) DEFAULT NULL,
  `nf_servings_unit` varchar(255) DEFAULT NULL,
  `nf_servings_weight_grams` decimal(65,0) DEFAULT NULL,
  `metric_qty` decimal(65,0) DEFAULT NULL,
  `metric_uom` varchar(255) DEFAULT NULL,
  `images_front_full_url` varchar(65) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `section_ids` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutrition`
--

LOCK TABLES `nutrition` WRITE;
#/*!40000 ALTER TABLE `nutrition` DISABLE KEYS */;
INSERT INTO `nutrition` VALUES (1,'Yummy Earth','Gummy Bears, Organic','51db37be176fe9790a898ee4','51c3ede297c3e6de73cbb4ee','10165014101',2,'Organic','Organic Rice Syrup, Organic Cane Sugar, Gelatin, Organic Aronia Juice, Organic Black Currant Juice, Natural Flavor, Citric Acid, Ascorbic Acid, Carnauba Wax, Organic Sunflower Oil. Gluten free.',0,0,0,0,NULL,NULL,0,0,21,0,12,0,0,0,0,0,NULL,5,11,'bears',28,28,'g',NULL,'2014-11-24',NULL),(2,'Nasoya','Vegi-Dressing, Creamy Dill','51db37cb176fe9790a89987f','51c36afc97c3e69de4b07667','25484000537',2,'Creamy Dill','Soymilk (Water, Organic Soybeans), Sunflower and/or Soybean Oil, Cane Juice (Dried), Vinegar,Salt, Xanthan Gum, Garlic Powder, Onion Powder, Lemon Juice Concentrate, Spices, Spinach (Dried), Natural Flavor.',60,7,0,NULL,NULL,NULL,0,140,1,0,1,0,0,0,0,0,NULL,11,2,'tbsp',30,30,'g',NULL,'2014-11-24',NULL),(3,'Barq\'s Diet','Creme Soda, French Vanilla Contour','51db37e3176fe9790a89a46d','51c5443d97c3e6efadd5d5c7','49000052008',2,'French Vanilla Contour','Carbonated Water, Natural And Artificial Flavors, Aspartame, Sodium Benzoate (to Protect Taste), Caramel Color, Citric Acid, Acacia',0,0,NULL,NULL,NULL,NULL,NULL,35,0,NULL,0,0,0,0,0,0,NULL,6,360,'ml',NULL,NULL,NULL,NULL,'2014-11-24',NULL),(4,'Our Family','Tomatoes, Stewed Italian','51db37b0176fe9790a8983b6','51c3e9f397c3e6de73cb9a63','70253267345',2,'Stewed Italian','Tomatoes, Tomato Juice, Sugar, Salt, Dehydrated Onion, Dehydrated Celery, Dehydrated Bell Peppers, Spices, Calcium Chloride, Citric Acid, Natural Flavors.',0,0,0,0,NULL,NULL,0,270,8,1,6,1,10,20,4,6,NULL,4,1,'cup',117,117,'g',NULL,'2014-11-24',NULL),(5,'jammin Nectars','Razz-Ade','51db37fa176fe9790a89abf9','51c3cc3d97c3e6d8d3b4e97a','12724000037',2,NULL,'Double Filtered Water, Raspberry Puree, Passion Fruit Puree, Evaporated Cane Juice, Lemon Juice Concentrate, Ascorbic Acid (Vitamin C), Citric Acid, Natural Flavor Extract, Natural Exotic Spices.',0,0,NULL,NULL,NULL,NULL,NULL,4,21,1,20,1,0,100,0,11,NULL,2,6,'fl oz',189,189,'g',NULL,'2014-11-24',NULL),(6,'Hannaford','Orange Juice','51db37b1176fe9790a898440','51c3d56997c3e6d8d3b53524','41268111268',2,NULL,'Filtered Water, Orange Juice Concentrate.',0,0,NULL,NULL,NULL,NULL,NULL,0,27,NULL,23,1,0,110,2,0,NULL,8,8,'fl oz',226,226,'g',NULL,'2014-11-24',NULL),(7,'Virginia Peanut Company','Peanuts, Brittle 8 Oz','51db37c9176fe9790a8996a6','51c549d897c3e6efadd6014e','30684840470',2,'Brittle 8 Oz','Peanuts, Syrup, Sugar, Baking Soda. May Contain Nut Traces.',40,4,1,0,NULL,NULL,0,10,18,1,10,4,0,0,0,0,NULL,12,28,'G',28,28,'g',NULL,'2014-11-24',NULL),(8,'PotatoFinger','Potato Chips, Lightly Salted','51db37d7176fe9790a899fa7','51c373f897c3e69de4b0c327','37578801005',2,'Lightly Salted','Potatoes, Vegetable Oil (Contains One or More of the Following: Cottonseed, Canola, or Corn Oil), and Salt.',90,9,3,0,NULL,NULL,0,115,16,0,0,2,0,20,0,4,NULL,8,1,'oz',28,28,'g',NULL,'2014-11-24',NULL),(9,'Alicita','Salsa, Twist of Greece, Medium','51db37dc176fe9790a89a0d1','51c3cdc297c3e6d8d3b4f59e','56952011267',2,'Twist of Greece, Medium','Diced Tomatoes, Crushed Tomatoes, Onions, Water, Apple Cider Vinegar, Jalapeno Peppers, Garlic, Cilantro, Sea Salt, Chili Pepper, Capers.',0,0,0,0,NULL,NULL,0,130,3,1,0,0,2,6,2,2,NULL,11,2,'tbsp',28,28,'g',NULL,'2014-11-24',NULL),(10,'America\'s Choice','Spinach, Chopped','51db37b0176fe9790a8983ea','51c3c89b97c3e6d8d3b4cba2','54807101859',2,'Chopped','Spinach.',0,0,0,0,NULL,NULL,0,120,3,1,0,2,50,2,8,4,NULL,6,1,'cup',75,75,'g',NULL,'2014-11-24',NULL),(11,'Nature\'s Own','Oatmeal Toasters, Cranberry Orange','51db37b9176fe9790a898bf4','51d379f3cc9bff5553aa9ca2','72250030663',2,'Cranberry Orange','Whole Wheat Flour, Water, Dried Cranberry Bits (Cranberries, Sugar, Sunflower Oil), Rolled Oats, Brown Sugar, Yeast, Sugar, Contains 2% Or Less of The Following.',15,1,0,0,NULL,NULL,0,210,35,4,9,5,0,0,2,10,NULL,8,64,'G',64,64,'g',NULL,'2014-11-24',NULL),(12,'De La Rosa','Pop, Jumbo Cherry With Bubble Gum Filling','51db37c6176fe9790a8994a5','51c54a1897c3e6efadd60358','25226000108',2,'Jumbo Cherry With Bubble Gum Filling','Sugar, Corn Syrup, Gum Base. Contains 2% Or Less of Citric Acid, Lactic Acid, Maltic Acid, Potassium Citrate, Sodium Lactate, Artificial Flavor, Red 40, Titanium Dioxide, Milk.',0,0,0,0,NULL,NULL,0,10,34,0,26,0,0,0,0,4,NULL,25,38,'G',38,38,'g',NULL,'2014-11-24',NULL),(13,'Shaw\'s','Cooking Spray, Vegetable Oil','51db37b4176fe9790a898834','51c3d1d097c3e6d8d3b51794','45674250191',2,'Vegetable Oil','Soybean Oil (Adds a Trivial Amount of Fat), Soy Lecithin, Dimethyl Silicone (for Anti-Foaming), Propellant.',0,0,0,0,NULL,NULL,0,0,0,0,0,0,0,0,0,0,NULL,565,0,'sec spray',0,0,'g',NULL,'2014-11-24',NULL),(14,'Crowley','Nonfat Yogurt, Lemon','51db37bb176fe9790a898db4','51c3bff197c3e6d8d3b48b26','71700449819',2,'Lemon','Cultured Pasteurized Nonfat Milk, Whey Protein Concentrate, Modified Food Starch, Whey, Lemon Flavor (preserved with Potassium Sorbate), Aspartame, Gelatin, Citric Acid, and Turmeric (for Color).',0,0,0,NULL,NULL,NULL,3,130,17,0,10,8,0,0,25,0,NULL,1,1,'ct',226,226,'g',NULL,'2014-11-24',NULL),(15,'ShopRite','Pork Roll, Mild, Hickory Smoked, Slices','51db37b9176fe9790a898bdb','51c37afc97c3e6d272824755','41190453498',2,'Mild, Hickory Smoked, Slices','Pork, Salt, Sugar, Sodium Nitrate, Sodium Nitrite.',100,11,5,NULL,NULL,NULL,25,450,1,NULL,1,6,0,0,0,2,NULL,4,1,'slice',42,42,'g',NULL,'2014-11-24',NULL),(16,'Smithfield','Sausage, Smoked','51db37b2176fe9790a8985ac','51c3c34297c3e6d8d3b4a591','70800050024',2,'Smoked','Pork, Water, Beef, Corn Syrup, Salt, Contains 2% or Less Flavorings, Dextrose, Sodium Phosphate, Sodium Erythorbate, Sodium Nitrite.',210,23,8,0,NULL,NULL,55,810,4,0,1,12,0,0,2,4,NULL,5,1,'link',90,90,'g',NULL,'2014-11-24',NULL),(17,'Royal Dansk','Luxury Wafers, Vanilla','51db37ba176fe9790a898cbd','51c3f06297c3e6de73cbc594','77330570060',2,'Vanilla','Sugar, Wheat Flour, Palm Oil, Tapioca Starch, Dextrin, Whey, Rice Flour, Milk, Dairy Creamer (Whey, Vegetable Oils [Coconut, Palm], Natural Flavor, Soy Lecithin), Salt, Soy Lecithin, Artificial Flavors (Vanilla, Milk), and Eggs.',45,5,2,0,NULL,NULL,0,70,21,0,7,1,0,0,0,0,NULL,13,3,'pieces',29,29,'g',NULL,'2014-11-24',NULL),(18,'Eagle Brand','Milk, Sweetened Condensed, Low Fat','51db37c3176fe9790a899241','51c38fe897c3e6d3ebc5c430','52729102138',2,'Sweetened Condensed, Low Fat','Concentrated Whole Milk, Concentrated Nonfat Milk, Sugar, Vitamin A Palmitate.',15,1,1,0,NULL,NULL,5,40,24,0,24,3,2,0,10,0,NULL,10,2,'tbsp',40,40,'g',NULL,'2014-11-24',NULL),(19,'New England Country Soup','Soup, Caribbean Black Bean','51db37e0176fe9790a89a2df','51c4041a97c3e6dfa4df524c','72010825119',2,'Caribbean Black Bean','Water, Black Beans, Green Peppers, Red Peppers, Onions, Rice Starch, Chili Peppers, Jalapeno Peppers, Olive Oil, Lower Sodium Sea Salt, Sugar, Cumin, Allspice, Nutmeg.',20,2,0,0,NULL,NULL,0,230,38,8,4,10,20,50,8,15,NULL,2,1,'cup',212,212,'g',NULL,'2014-11-24',NULL),(20,'Smithfield','Sausage, Smoked','51db37b2176fe9790a8985ac','51c3c34297c3e6d8d3b4a591','70800050024',2,'Smoked','Pork, Water, Beef, Corn Syrup, Salt, Contains 2% or Less Flavorings, Dextrose, Sodium Phosphate, Sodium Erythorbate, Sodium Nitrite.',210,23,8,0,NULL,NULL,55,810,4,0,1,12,0,0,2,4,NULL,5,1,'link',90,90,'g',NULL,'2014-11-24',NULL),(21,'Salute Sante!','Grapeseed Oil','51db37e6176fe9790a89a6aa','51c3d1bf97c3e6d8d3b516fa','56240009945',2,NULL,'Grapeseed Oil.',120,14,1,0,NULL,NULL,0,0,0,NULL,NULL,0,0,0,0,0,NULL,68,1,'tbsp',14,14,'g',NULL,'2014-11-24',NULL),(22,'Santa Cruz','Juice, White Grape','51db37c2176fe9790a8991d8','51c3722d97c3e69de4b0b3c9','36192122893',2,'White Grape','Filtered Water (Sufficient to Reconstitute), Organic White Grape Juice Concentrate. Gluten free.',0,0,NULL,NULL,NULL,NULL,NULL,20,39,NULL,39,0,0,0,0,0,NULL,4,8,'fl oz',226,226,'g',NULL,'2014-11-24',NULL),(23,'Our Family','Tomatoes, Stewed Italian','51db37b0176fe9790a8983b6','51c3e9f397c3e6de73cb9a63','70253267345',2,'Stewed Italian','Tomatoes, Tomato Juice, Sugar, Salt, Dehydrated Onion, Dehydrated Celery, Dehydrated Bell Peppers, Spices, Calcium Chloride, Citric Acid, Natural Flavors.',0,0,0,0,NULL,NULL,0,270,8,1,6,1,10,20,4,6,NULL,4,1,'cup',117,117,'g',NULL,'2014-11-24',NULL),(24,'Devonsheer','Melba Rounds, Sesame 5.25 Oz','51db37ba176fe9790a898d18','51c5497997c3e6efadd5fe88','30684814839',2,'Sesame 5.25 Oz','Organic Wheat Flour, Organic Sesame Seeds, Organic Canola Oil, Sea Salt, Organic Molasses, Yeast, Organic Wheat Gluten, Organic Vinegar. Contains: Wheat.',15,2,0,0,NULL,NULL,0,100,11,1,0,2,0,0,0,4,NULL,10,15,'G',15,15,'g',NULL,'2014-11-24',NULL),(25,'Bumble Bee','Red Salmon, Wild Alaska Sockeye','51db37be176fe9790a898ef5','51c3edfa97c3e6de73cbb58a','86600000640',2,'Wild Alaska Sockeye','Sockeye Salmon, Salt.',60,7,1,0,NULL,NULL,40,270,0,0,0,13,2,0,10,2,NULL,7,0,'cup',63,63,'g',NULL,'2014-11-24',NULL),(26,'Kroger','Cheese Snacks, Twists','51db37b9176fe9790a898bdd','51c35a2597c3e69de4affba6','11110587923',2,'Twists','Pasteurized Part-Skim Milk, Cream and Skim Milk, Cheese Cultures, Salt, Annatto (for Color), Enzymes.',50,6,3,0,NULL,NULL,15,200,0,0,0,6,4,0,20,0,NULL,12,1,'pc',28,28,'g',NULL,'2014-11-24',NULL),(27,'Fratelli Beretta','Pancetta','51db37b6176fe9790a89895b','51c3c23497c3e6d8d3b49d58','73541405269',2,NULL,'Pork Belly, Salt, and Less than 2% of the Following: Natural Flavorings, Sugar, Sodium Nitrate, Sodium Ascorbate, Sodium Nitrite, Lactic Acid Starter Culture.',60,11,4,0,NULL,NULL,25,410,0,0,0,5,0,0,0,0,NULL,4,1,'oz',28,28,'g',NULL,'2014-11-24',NULL),(28,'Cap Candy','Spin Pop Candy, Toy Story 2, Woody','51db37c6176fe9790a899477','51c3694b97c3e69de4b067b4','22629045712',2,'Toy Story 2, Woody','Sugar, Corn Syrup. Starch, Citric Acid, Glycerin, Artificial Flavors, Artificial Colors (Including FD&C Blue No. 1), Turmeric Coloring, and BHT Added as a Preservative.',0,0,NULL,NULL,NULL,NULL,NULL,0,14,NULL,12,0,0,0,10,0,NULL,1,1,'pop',NULL,NULL,NULL,NULL,'2014-11-24',NULL),(29,'Pacific Gold','Pistachios','51db37cb176fe9790a8998aa','51c3643597c3e69de4b041de','20022010634',2,NULL,'Pistachios, Salt.',130,16,2,0,NULL,NULL,0,230,8,3,2,4,2,4,4,6,NULL,5,0,'cup',28,28,'g',NULL,'2014-11-24',NULL),(30,'Tuf','Bread Pudding Mix','51db37e3176fe9790a89a4df','51c357cd97c3e69de4afefea','645001028',2,NULL,'Sugar, Coconut Oil, Corn Syrup Solids, Egg Yolk Solids, Egg White Solids, Nonfat Dry Milk and/or Sodium Caseinate, Artificial Flavor, Calcium & Potassium Phosphate, Sodium Stearoyl Lactylate, Soy Lecithin, Annatto (Color), Mono & Diglycerides.',35,4,3,0,NULL,NULL,25,15,29,0,26,1,0,0,0,0,NULL,16,3,'tbsp dry',34,34,'g',NULL,'2014-11-24',NULL),(31,'S&B','Sauce with Vegetables, Golden Curry, Hot','51db37ce176fe9790a899c64','51c3d27397c3e6d8d3b51cc9','74880040623',2,'Golden Curry, Hot','Water, Vegetables (Potato, Onion, Carrot), Edible Oils (Palm Oil, Canola Oil), Wheat Flour, Sugar, Curry Powder, Hydroxypropyl Distarch Phosphate, Salt, Yeast Extract, Spices, Caramel, Apple Vinegar, Dried Bonito Powder, Dextrin.',130,15,9,0,NULL,NULL,0,1100,25,3,7,3,6,4,6,6,NULL,1,1,'package',230,230,'g',NULL,'2014-11-24',NULL),(32,'Strathmore','Carbonated Natural Mineral Water, Lime','51db37ef176fe9790a89a908','51c3613e97c3e69de4b028c5','15516012669',2,'Lime','Water, Carbon Dioxide, Natural Lime Flavor.',0,0,NULL,NULL,NULL,NULL,NULL,15,0,NULL,NULL,0,0,0,4,0,NULL,6,8,'fl oz',239,239,'g',NULL,'2014-11-24',NULL);
#/*!40000 ALTER TABLE `nutrition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
#/*!40101 SET @saved_cs_client     = @@character_set_client */;
#/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `UID` int NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `UserLevel` varchar(45) NOT NULL,
  `FavoriteRecipeID` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UID_UNIQUE` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
#/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Cjhosier','CJhosier@iastate.edu','FUCKOFF','ADMIN','1');
#/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
#/*!40101 SET @saved_cs_client     = @@character_set_client */;
#/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `UID` int NOT NULL,
  `Recipe_name` varchar(100) NOT NULL,
  `Recipe_Steps` varchar(300) NOT NULL,
  `nid` int DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `UID_idx` (`UID`),
  KEY `nid_idx` (`nid`),
  CONSTRAINT `nid` FOREIGN KEY (`nid`) REFERENCES `nutrition` (`nid`),
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `user_info` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
#/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,1,'Hot Orange Juice','put orange juice into a pot, boil on high for 10 minutes, reevaluate your life, serve hot ',6);
#/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

#/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */
#/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
#/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
#/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
#/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
#/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
#/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
#/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-02 10:02:19
