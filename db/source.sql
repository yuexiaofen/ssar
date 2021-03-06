USE [source]
GO
/****** Object:  Table [dbo].[class]    Script Date: 2020/6/16 10:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[class](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[number] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_class] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[score]    Script Date: 2020/6/16 10:01:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[score](
	[stu_id] [int] NOT NULL,
	[sub_id] [int] NULL,
	[score] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[student]    Script Date: 2020/6/16 10:01:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[student](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[number] [int] NOT NULL,
	[age] [int] NULL,
	[birthday] [datetime] NULL,
	[class_id] [int] NOT NULL,
 CONSTRAINT [PK_student] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subject]    Script Date: 2020/6/16 10:01:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subject](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](500) NULL,
 CONSTRAINT [PK_subject] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[score]  WITH CHECK ADD  CONSTRAINT [FK_Score_student] FOREIGN KEY([stu_id])
REFERENCES [dbo].[student] ([id])
GO
ALTER TABLE [dbo].[score] CHECK CONSTRAINT [FK_Score_student]
GO
ALTER TABLE [dbo].[score]  WITH CHECK ADD  CONSTRAINT [FK_Score_subject] FOREIGN KEY([sub_id])
REFERENCES [dbo].[subject] ([id])
GO
ALTER TABLE [dbo].[score] CHECK CONSTRAINT [FK_Score_subject]
GO
