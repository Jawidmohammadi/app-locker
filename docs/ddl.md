## SQL Data Definition Language (DDL)

```sqlite
CREATE TABLE IF NOT EXISTS `Apps`
(
    `application_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `package`            TEXT COLLATE NOCASE,
    `restriction_method` TEXT COLLATE NOCASE,
    `hashed_password`    TEXT
);
CREATE UNIQUE INDEX IF NOT EXISTS `index_App_package` ON `Apps` (`package`);
CREATE INDEX IF NOT EXISTS `index_App_hashed_password` ON `Apps` (`hashed_password`);

CREATE TABLE IF NOT EXISTS `Attempt`
(
    `attempt_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `application_id` INTEGER                           NOT NULL,
    `time_stamp`     INTEGER,
    `success`        INTEGER                           NOT NULL,
    FOREIGN KEY (`application_id`) REFERENCES `Apps` (`application_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS `index_Attempt_application_id` ON `Attempt` (`application_id`);
CREATE INDEX IF NOT EXISTS `index_Attempt_time_stamp` ON `Attempt` (`time_stamp`);
CREATE INDEX IF NOT EXISTS `index_Attempt_success` ON `Attempt` (`success`);
````
