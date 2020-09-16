CREATE TABLE `account` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `confirmed_and_active` tinyint(1) NOT NULL DEFAULT '0',
  `member_since` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password_hash` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `photo_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `support` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `ix_account_email` (`email`),
  KEY `ix_account_phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;