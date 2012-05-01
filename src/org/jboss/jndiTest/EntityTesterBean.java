/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.jndiTest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@Remote(EntityTester.class)
@SecurityDomain("alfa-db")
public class EntityTesterBean implements EntityTester
{
	
   private @PersistenceContext(unitName="jpa-test") EntityManager manager;
   
   @RolesAllowed("dummyRole")
   public void createEntity(long id){
	   TestEntity t = new TestEntity();
	   t.setId(id);
	   manager.persist(t);
	   
	   System.out.println("!!!!! persisted " + id);
   }
	
   public void findEntity(long id){
	   TestEntity t = manager.find(TestEntity.class, id);
	      
	   System.out.println("!!!!!!! Found entity " + t);
	   
   }
}
