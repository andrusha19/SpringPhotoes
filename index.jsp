<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog.kiev.ua</title>
  </head>
  <body>
     <div align="center">
        <form action="/spr2/view" method="POST">
            Photo id: <input type="text" name="photo_id">
            <input type="submit" value="Search photo"/>
        </form>

        <form action="/spr2/add_photo" enctype="multipart/form-data" method="POST">
            Photo: <input type="file" name="photo">
            <input type="submit" value="Send photo"/>
        </form>
         <input type="submit" value="View list of photo IDs" onclick="window.location='/spr2/viewlist';" />
         <input type="submit" value="View all photos" onclick="window.location='/spr2/viewall';" />
      </div>
  </body>
</html>
