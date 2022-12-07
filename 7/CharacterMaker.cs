using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// //[System.Serializable]
// public class CharacterMaker : MonoBehaviour
// {
//     public static GameObject AIObject;

//     public string name;

//     public CharacterMaker(string name){
//         this.name = name;
//     }


//     public struct CharacterCreator{
//         public string name{get; set;}
//         public GameObject playerObject{get; set;}

//         public CharacterCreator(string name){
//             this.name = name;
//         }
//     }

//     abstract class CharacterCreator : Celebrate{
//         float moveSpeed;
//         float jumpForce;

//         //CharacterController controller;

//         Vector3 moveDirection;

//         float gravityScale;
//         int hp;
//         //bool player;

//         public string type;


//         public CharacterCreator(CombatDecorator celebration) : base(celebration){
//             //super(celebration);

//             //this.player = player;

//             this.moveSpeed = 10;
//             this.jumpForce = 10;
//             this.gravityScale = 3;
//             this.hp = 3;
//             this.type = "none";
//         }

//         public virtual void move(){}

//         void takeDamage(int damage){}

//         public override string Shout(){
//             return base.Shout();
//         }
//         public override string Dance(){
//             return base.Dance();
//         }
//         public override string Jump(){
//             return base.Jump();
//         }
//         public override string Spin(){
//             return base.Spin();
//         }
//     }

//     class Player : CharacterCreator{
//         public Player(CombatDecorator celebration) : base(celebration){
//             type = "Player";
//         }

//         public override void move(){
//             FindObjectOfType<PlayerController>().move();
//             Debug.Log("yo");
//         }
//     }

    

//     // *********************************** Decorator Thing ********************************
//     interface CombatDecorator{
//         string Shout();
//         string Dance();
//         string Jump();
//         string Spin();
//     }

//     //class Decorate Implements CombatDecorator{
//     class Decorate : CombatDecorator{
//         public string Shout(){
//             return "shout ";
//         }
//         public string Dance(){
//             return "dance ";
//         }
//         public string Jump(){
//             return "jump ";
//         }
//         public string Spin(){
//             return "spin ";
//         }
//     }

//     abstract class Celebrate : CombatDecorator{
//         protected CombatDecorator celebration;
 
//         // Abstract class method
//         public Celebrate(CombatDecorator celebration){
//             // This keywordd refers to current object itself
//             this.celebration = celebration;
//             //System.out.println(this.celebration);
//         }
    
//         // Outside abstract class
//         public virtual string Shout() {
//             return(celebration.Shout());
//         }
//         public virtual string Dance() {
//             return(celebration.Dance());
//         }
//         public virtual string Jump() {
//             return(celebration.Jump());
//         }
//         public virtual string Spin() {
//             return(celebration.Spin());
//         }
//     }
// }
