// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import java.util.logging.Logger;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

    private ArrayList<String> comments= new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonComments = convertStringToJson(comments);        
        try 
        {
            response.setContentType("application/json;");
            response.getWriter().println(jsonComments);
        }
        catch (IOException e)
        {
            System.err.println("Failed: ");
            e.printStackTrace();
        }
    }
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
        String text= getParameter(request,"add-comment","");
        comments.add(text);
        try{
            System.out.println(comments);
            response.sendRedirect("/index.html");
        }
        catch (IOException e)
        {
            System.err.println("Failed: ");
            e.printStackTrace();
        }
  }

  public String getParameter(HttpServletRequest request, String text, String defaultValue)
  {
        String value= request.getParameter(text);
        if(value=="")
        {
            return defaultValue;
        }
        return value;
  }

  private String convertStringToJson(ArrayList<String> comments)
  {
      Gson gson = new Gson();
      return gson.toJson(comments);
  }
}
