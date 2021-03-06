USE [ssar]
GO
/****** Object:  Table [dbo].[ssar_class]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_class](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[grade_id] [bigint] NOT NULL,
	[class_name] [varchar](255) NOT NULL,
	[enable] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_class_user]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_class_user](
	[class_id] [bigint] NOT NULL,
	[user_id] [bigint] NOT NULL,
	[id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[class_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_dictionary]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_dictionary](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[allow_empty] [varchar](255) NULL,
	[column_description] [varchar](255) NULL,
	[column_name] [varchar](255) NULL,
	[data_type] [varchar](255) NULL,
	[decimal_places] [varchar](255) NULL,
	[defaults] [varchar](255) NULL,
	[length] [varchar](255) NULL,
	[logo] [varchar](255) NULL,
	[primary_key] [varchar](255) NULL,
	[serial_number] [varchar](255) NULL,
	[table_name] [varchar](255) NULL,
	[training_library_id] [bigint] NOT NULL,
 CONSTRAINT [PK__ssar_dic__3213E83FAC1BE6B9] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_grade]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_grade](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[year] [bigint] NULL,
	[enable] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_permission]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_permission](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[description] [varchar](255) NULL,
	[enable] [bit] NULL,
	[name] [varchar](255) NOT NULL,
	[permission_key] [varchar](32) NOT NULL,
	[type] [varchar](255) NULL,
	[weight] [int] NULL,
	[parent_id] [bigint] NULL,
	[path] [varchar](255) NULL,
	[resource] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_practice]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_practice](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[create_time] [datetime2](7) NULL,
	[done] [bit] NULL,
	[enable] [bit] NULL,
	[practice_name] [varchar](255) NOT NULL,
	[score] [float] NULL,
	[update_time] [datetime2](7) NULL,
	[user_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_practice_class]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_practice_class](
	[practice_id] [bigint] NOT NULL,
	[class_id] [bigint] NOT NULL,
	[id] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_practice_training_library]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_practice_training_library](
	[practice_id] [bigint] NOT NULL,
	[training_library_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_role]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_role](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[description] [varchar](255) NULL,
	[enable] [bit] NULL,
	[role_key] [varchar](32) NOT NULL,
	[role_name] [varchar](32) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_rcpd97qd3khbxhipheltb7onl] UNIQUE NONCLUSTERED 
(
	[role_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_sbjosicr2q4835gcxo6grjvcs] UNIQUE NONCLUSTERED 
(
	[role_key] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_role_permission]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_role_permission](
	[role_id] [bigint] NOT NULL,
	[permission_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC,
	[permission_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_score]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_score](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[answer] [varchar](255) NULL,
	[create_time] [datetime2](7) NULL,
	[enable] [bit] NULL,
	[score] [float] NULL,
	[tips] [varchar](255) NULL,
	[update_time] [datetime2](7) NULL,
	[practice_id] [bigint] NOT NULL,
	[student_id] [bigint] NOT NULL,
	[training_library_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_training_library]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_training_library](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[problem] [varchar](255) NULL,
	[reference_answer] [varchar](255) NULL,
	[type] [varchar](255) NULL,
	[user_id] [bigint] NOT NULL,
	[answer] [varchar](255) NULL,
	[create_time] [datetime2](7) NULL,
	[enable] [bit] NULL,
	[name] [varchar](255) NOT NULL,
	[update_time] [datetime2](7) NULL,
	[description] [varchar](500) NULL,
	[practice_id] [bigint] NOT NULL,
	[total_score] [float] NULL,
 CONSTRAINT [PK__ssar_tra__3213E83FD67FBAB0] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_training_library_practice]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_training_library_practice](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[practice_id] [bigint] NOT NULL,
	[training_library_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_user]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_user](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[account] [varchar](16) NOT NULL,
	[email] [varchar](255) NULL,
	[enable] [bit] NULL,
	[password] [varchar](128) NOT NULL,
	[tel] [varchar](255) NULL,
	[user_name] [varchar](32) NULL,
	[birthday] [datetime2](7) NULL,
	[salt] [varchar](255) NULL,
	[sex] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_nj853k6b6xyvjcdrrpa31p6e1] UNIQUE NONCLUSTERED 
(
	[account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ssar_user_role]    Script Date: 2020/6/16 9:59:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ssar_user_role](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ssar_class]  WITH CHECK ADD  CONSTRAINT [FK5rnrrbx9jmgd64pppx11o8vt3] FOREIGN KEY([grade_id])
REFERENCES [dbo].[ssar_grade] ([id])
GO
ALTER TABLE [dbo].[ssar_class] CHECK CONSTRAINT [FK5rnrrbx9jmgd64pppx11o8vt3]
GO
ALTER TABLE [dbo].[ssar_class_user]  WITH CHECK ADD  CONSTRAINT [FK48fjoubrgp900gdkkjebqppgg] FOREIGN KEY([class_id])
REFERENCES [dbo].[ssar_class] ([id])
GO
ALTER TABLE [dbo].[ssar_class_user] CHECK CONSTRAINT [FK48fjoubrgp900gdkkjebqppgg]
GO
ALTER TABLE [dbo].[ssar_class_user]  WITH CHECK ADD  CONSTRAINT [FKeav0jbrmo2l7qasfmy4b8x4qd] FOREIGN KEY([user_id])
REFERENCES [dbo].[ssar_user] ([id])
GO
ALTER TABLE [dbo].[ssar_class_user] CHECK CONSTRAINT [FKeav0jbrmo2l7qasfmy4b8x4qd]
GO
ALTER TABLE [dbo].[ssar_dictionary]  WITH CHECK ADD  CONSTRAINT [FKalv67wx9j0jlh3g7sp5vbcl7p] FOREIGN KEY([training_library_id])
REFERENCES [dbo].[ssar_training_library] ([id])
GO
ALTER TABLE [dbo].[ssar_dictionary] CHECK CONSTRAINT [FKalv67wx9j0jlh3g7sp5vbcl7p]
GO
ALTER TABLE [dbo].[ssar_permission]  WITH CHECK ADD  CONSTRAINT [FK95a4nqqdh4g0ce13fqpyvc3os] FOREIGN KEY([parent_id])
REFERENCES [dbo].[ssar_permission] ([id])
GO
ALTER TABLE [dbo].[ssar_permission] CHECK CONSTRAINT [FK95a4nqqdh4g0ce13fqpyvc3os]
GO
ALTER TABLE [dbo].[ssar_practice]  WITH CHECK ADD  CONSTRAINT [FKn59b5it8h4q1qn525qkvucm38] FOREIGN KEY([user_id])
REFERENCES [dbo].[ssar_user] ([id])
GO
ALTER TABLE [dbo].[ssar_practice] CHECK CONSTRAINT [FKn59b5it8h4q1qn525qkvucm38]
GO
ALTER TABLE [dbo].[ssar_practice_class]  WITH CHECK ADD  CONSTRAINT [FKc93psee9ltjdp819c7bf28lcr] FOREIGN KEY([class_id])
REFERENCES [dbo].[ssar_class] ([id])
GO
ALTER TABLE [dbo].[ssar_practice_class] CHECK CONSTRAINT [FKc93psee9ltjdp819c7bf28lcr]
GO
ALTER TABLE [dbo].[ssar_practice_class]  WITH CHECK ADD  CONSTRAINT [FKiu8s0k9lda169bnpb7qxr923d] FOREIGN KEY([practice_id])
REFERENCES [dbo].[ssar_practice] ([id])
GO
ALTER TABLE [dbo].[ssar_practice_class] CHECK CONSTRAINT [FKiu8s0k9lda169bnpb7qxr923d]
GO
ALTER TABLE [dbo].[ssar_practice_training_library]  WITH CHECK ADD  CONSTRAINT [FK45r12xh4ta8weihj03kofykfe] FOREIGN KEY([training_library_id])
REFERENCES [dbo].[ssar_training_library] ([id])
GO
ALTER TABLE [dbo].[ssar_practice_training_library] CHECK CONSTRAINT [FK45r12xh4ta8weihj03kofykfe]
GO
ALTER TABLE [dbo].[ssar_practice_training_library]  WITH CHECK ADD  CONSTRAINT [FKqsbe5hh988e59w8en4rhpf8w8] FOREIGN KEY([practice_id])
REFERENCES [dbo].[ssar_practice] ([id])
GO
ALTER TABLE [dbo].[ssar_practice_training_library] CHECK CONSTRAINT [FKqsbe5hh988e59w8en4rhpf8w8]
GO
ALTER TABLE [dbo].[ssar_role_permission]  WITH CHECK ADD  CONSTRAINT [FKiypclntcqe1p4f3mwh8nbpwsl] FOREIGN KEY([permission_id])
REFERENCES [dbo].[ssar_permission] ([id])
GO
ALTER TABLE [dbo].[ssar_role_permission] CHECK CONSTRAINT [FKiypclntcqe1p4f3mwh8nbpwsl]
GO
ALTER TABLE [dbo].[ssar_role_permission]  WITH CHECK ADD  CONSTRAINT [FKlcm2uc066e8cwj5eyb2g3fvei] FOREIGN KEY([role_id])
REFERENCES [dbo].[ssar_role] ([id])
GO
ALTER TABLE [dbo].[ssar_role_permission] CHECK CONSTRAINT [FKlcm2uc066e8cwj5eyb2g3fvei]
GO
ALTER TABLE [dbo].[ssar_score]  WITH CHECK ADD  CONSTRAINT [FK3qd4yb1yq6eanhaajd1m16rct] FOREIGN KEY([training_library_id])
REFERENCES [dbo].[ssar_training_library] ([id])
GO
ALTER TABLE [dbo].[ssar_score] CHECK CONSTRAINT [FK3qd4yb1yq6eanhaajd1m16rct]
GO
ALTER TABLE [dbo].[ssar_score]  WITH CHECK ADD  CONSTRAINT [FKaksqtd6p0i2qpsia4mtv8pl06] FOREIGN KEY([student_id])
REFERENCES [dbo].[ssar_user] ([id])
GO
ALTER TABLE [dbo].[ssar_score] CHECK CONSTRAINT [FKaksqtd6p0i2qpsia4mtv8pl06]
GO
ALTER TABLE [dbo].[ssar_score]  WITH CHECK ADD  CONSTRAINT [FKu8ni7tesx8hv4vg96o062jkl] FOREIGN KEY([practice_id])
REFERENCES [dbo].[ssar_practice] ([id])
GO
ALTER TABLE [dbo].[ssar_score] CHECK CONSTRAINT [FKu8ni7tesx8hv4vg96o062jkl]
GO
ALTER TABLE [dbo].[ssar_training_library]  WITH CHECK ADD  CONSTRAINT [FKrqvl44dmmsu9f1tg0iin3lprs] FOREIGN KEY([user_id])
REFERENCES [dbo].[ssar_user] ([id])
GO
ALTER TABLE [dbo].[ssar_training_library] CHECK CONSTRAINT [FKrqvl44dmmsu9f1tg0iin3lprs]
GO
ALTER TABLE [dbo].[ssar_training_library]  WITH CHECK ADD  CONSTRAINT [FKt8k0vkk23gdeybyf45a5vfsl] FOREIGN KEY([practice_id])
REFERENCES [dbo].[ssar_practice] ([id])
GO
ALTER TABLE [dbo].[ssar_training_library] CHECK CONSTRAINT [FKt8k0vkk23gdeybyf45a5vfsl]
GO
ALTER TABLE [dbo].[ssar_training_library_practice]  WITH CHECK ADD  CONSTRAINT [FK456gn49ed9f7t6v31uul0geip] FOREIGN KEY([practice_id])
REFERENCES [dbo].[ssar_practice] ([id])
GO
ALTER TABLE [dbo].[ssar_training_library_practice] CHECK CONSTRAINT [FK456gn49ed9f7t6v31uul0geip]
GO
ALTER TABLE [dbo].[ssar_training_library_practice]  WITH CHECK ADD  CONSTRAINT [FKjdf19id27nvd2u584f10g5dt8] FOREIGN KEY([training_library_id])
REFERENCES [dbo].[ssar_training_library] ([id])
GO
ALTER TABLE [dbo].[ssar_training_library_practice] CHECK CONSTRAINT [FKjdf19id27nvd2u584f10g5dt8]
GO
ALTER TABLE [dbo].[ssar_user_role]  WITH CHECK ADD  CONSTRAINT [FK2ycl58j1kdv1owd02nxwjua8f] FOREIGN KEY([user_id])
REFERENCES [dbo].[ssar_user] ([id])
GO
ALTER TABLE [dbo].[ssar_user_role] CHECK CONSTRAINT [FK2ycl58j1kdv1owd02nxwjua8f]
GO
ALTER TABLE [dbo].[ssar_user_role]  WITH CHECK ADD  CONSTRAINT [FK97nppg3mkm8pusq10abe1vv4b] FOREIGN KEY([role_id])
REFERENCES [dbo].[ssar_role] ([id])
GO
ALTER TABLE [dbo].[ssar_user_role] CHECK CONSTRAINT [FK97nppg3mkm8pusq10abe1vv4b]
GO
